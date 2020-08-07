package kr.co.jhta.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.jhta.form.BoardForm;
import kr.co.jhta.service.BoardService;
import kr.co.jhta.view.FileDownloadView;
import kr.co.jhta.vo.Board;
import kr.co.jhta.vo.User;

/*
 * Spring MVC의 주요 어노테이션
 * 		@Controller
 * 			-컨트롤러 역할을 수행하는 객체임을 나타내는 어노테이션이다.
 * 		@RequestMapping
 * 			-요청 URI와 컨트롤러 혹은 요청 URI와 요청 핸들러 메소드를 매핑시킨다.
 * 			-클래스와 메소드에 각각 부착할 수 있다.
 * 			-클래스에 부착할 경우, 해당 클래스의 정의된 모든 요청 핸들러 메소드의 
 * 			 매핑URI에 접두사로 추가된다.
 * 			-하나 이상의 URI를 지정할 수 있다.
 * 			 @RequestMapping(value={"/", "/home.do", "/main.do"})
 * 			-주요 속성
 * 			 value
 * 				매핑 URI를 지정한다.
 * 				@RequestMapping("/home.do")는 @RequestMapping(value="/home.do")와 동일하다.
 * 				value="/home.do"
 * 				value={"/home.do","/main.do"}
 * 			 path
 * 				매핑 URI를 지정한다.
 * 				사용법은 value와 동일하다.
 * 			 method
 * 				요청 방식(GET, POST)을 지정한다.
 * 				method=RequestMethod.GET
 * 				method=RequestMethod.POST
 * 				method=RequestMethod.PUT
 * 				method=RequestMethod.DELETE
 * 				@RequestMapping(value="/login.do", method=RequestMethod.GET)
 * 					GET방식의 login.do 요청에 매핑된다.
 * 					@RequestMapping(value="/login.do", method=RequestMethod.GET)
 * 					public String loginform() {
 * 						//로그인 폼을 응답으로 보내는 요청 처리
 * 						return "user/loginform";
 * 					}
 * 				@RequestMapping(value="/login.do", method=RequestMethod.POST)
 * 					POST방식의 login.do 요청에 매핑된다.
 * 					@RequestMapping(value="/login.do", method=RequestMethod.POST)
 * 					public String login(@RequestParam("id") String id, 
 * 										@RequestParam("password") String password) {
 * 						//아이디, 비밀번호를 POST 요청방식으로 서버로 보냈을 떄 요청 처리
 * 						return "";
 * 					}
 * 
 * 			 consumes
 * 				매핑된 요청의 소비가능한 미디어 타입을 지정한다.
 * 				consumes="application/json"
 * 					클라이언트가 서버로 보내는 컨테츠 타입이 JSON 형식의 값일 때 매핑된다.
 * 			 produces
 * 				매핑된 요청의 생성가능한 응답 미디어 타입을 지정한다.
 * 				produces="application/json"
 * 					서버가 클라이언트로 보내는 컨텐츠 타입이 JSON현실의 값인 경우로,
 * 					클라이언트측에서 기대되는 응답 컨텐츠의 타입을 JSON이라고 지정한 경우
 * 					이 요청헨들러 메소드와 매핑된다.
 * 		@RequestParam
 *			-요청파라미터값을 매개변수와 매핑시키는 어노테이션이다.
 *			-메소드의 매개변수 선언앞에 부착한다.
 *			-HandlerAdapter가 요청 핸들러 메소드의 @RequestParam과 매개변수를 분석해서
 *			 적절한 요청파라미터 값을 찾아서 매개변수에 전달한다.
 *			-주요 속성
 *			 value
 *				요청파라미터 명을 지정한다.
 *				value="userid"
 *				handlerMethod(@RequestParam(value="userid") String id)
 *				handlerMethod(@RequestParam(name="userid") String id)
 *				handlerMethod(@RequestParam("userid") String id)
 *				*위 세가지 모드 요청파라미터명이 userId인 요청파라미터값을 찾아서
 *				 매개변수 id에 전달한다.
 *				handlerMethod(String userId)
 *				*@RequestParam을 생략하면 매개변수 이름이 곧 요청파라미터 명이다.
 *				 즉, 위의 선언은 handlerMethod(@RequestParam(value="userId") String userId)와
 *				 동일하다.
 *				handlerMethod(@RequestParam("skill") String[] skills)
 *				*<form /> 태그 안에 <input name="skill">이 여러 개인 경우
 *				 체크된 혹은 입력된 값을 배열로 받을 수 있다.
 *				*** 주의 사항) 반드시 지정한 이름의 요청파라미터가 존재해야 한다.
 *			 name
 *				요청파라미터 명을 지정한다.
 *				value의 사용법과 동일하다.
 *			 required
 *				필수 요청파라미터 여부를 지정한다. 기본값이 true다.
 *				required=false로 지정하지 않은 이상 무조건 요청파라미터가 존재해야 한다.
 *				required=false로 지정하는 경우 거의 대부분 defaultValue와 같이 사용된다.
 *			 defaultValue
 *				요청 파라미터 값이 비어 있을 때 기본 값을 지정한다.
 *				required=false로 지정된 경우 defaultValue를 사용해서 기본값을
 *				지정할 수 있다.
 *				(@RequestParam(name="pageNo", required=false, defaultValue="1") int pageNo)
 *				(@RequestParam(name="type", required=false, defaultValue="국내도서") String bookType)
 */
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileDownloadView fileDownloadView;
	
	@Value("${directory.save.freeboard}")
	private String saveDirectory;
	
	//예외처리하기
	@ExceptionHandler(RuntimeException.class)
	public String runtimeExceptionHandler(RuntimeException e) {
		e.printStackTrace();
		return "error/server-error";
	}
	
	@ExceptionHandler(Exception.class) 
	public String ExceptionHandler(Exception e) {
		e.printStackTrace();
		return "error/server-unknown-error";
	}
	
	//@RequestMapping(value="/list.do", method=RequestMethod.GET)
	@GetMapping("/list.do")
	public String boardList(Model model) {
		// 게시글 전체 조회
		List<Board> allBoards = boardService.getAllBoards();
		// 조회된 게시글을 Model에 담아서 View page에 전달하기
		// Model 객체는 파라미터로 지정해놓기만 하면 HandlerAdapter가 확인 후 생성해서 전달해준다.
		model.addAttribute("boards", allBoards);
		
		// 내부이동할 JSP 페이지의 경로 및 이름 반환
		return "board/list";
	}
	
	//@RequestMapping(value="/add.do", method=RequestMethod.GET)
	@GetMapping("/add.do")
	public String form() {
		return "board/form";
	}
	
	//@RequestMapping(value="/add.do",method=RequestMethod.POST)
	@PostMapping("/add.do")
	//폼 커맨드 객체를 매개변수로 선언하기
	//* <form />태그의 모든 입력필드값을 저장하는 폼 커맨드 객체를 요청핸들러 메소드의
	//  매개변수로 선언하면, HandlerAdapter가 요청파라미터값을 조회해서 
	//  폼 커맨드객체에 자동으로 저장한 후, 요청핸들러 메소드 실행시 전달해준다.
	//* <form /> 태그의 입력필드갯수가 많을 때 반복적인 코드를 효과적으로 줄일 수 있다.
	public String addBoard(BoardForm boardForm, User user) throws Exception {
		
		if(user == null) {
			return "redirect:/signup.do?error=deny";
		}
		
		Board board = new Board();
		board.setWriter(user.getId());
		
		//BeanUtils.copyProperties(원본, 대상)
		//*원본객체의 필드값을 대상객체의 필드에 복사한다.
		//*원본객체의 필드명과 대상객체의 필드명이 동일한 필드만 복사가 발생한다.
		//*원본객체와 대상객체의 필드명은 동일하지만, 타입이 서로 다를 떄는 에러가 발생한다.
		//*원본객체의 대상객체의 필드를 선택적으로 복사할 수 없다.
		//	(필드명은 동일하지만, 타입이 다른 필드가 존재하지 않아야 한다.)
		//title, writer, content, password 값이 boardForm에서 board로 복사된다.
		// BoardForm의 값들을 Board로 복사
		BeanUtils.copyProperties(boardForm, board);
		
		// 첨부파일 핸들링
		//MultipartFile
		//*첨부파일 업로드를 지원하는 객체다.
		//*<form />태그의 enctype="multipart/form-data"이고, 첨부파일 필드가 있고,
		// xxxForm객체에 첨부파일입력필드와 동일한 이름의 필드가 있으면
		// MultipartFile는 결코 null이 아니다.
		// (<input type="file"/>첨부파일 입력필드에서 첨부파일을 선택하지 않아도
		//  MultipartFile객체는 null이 아니다, 비어있을 뿐이다.)
		
		//*주요 API
		//	boolean isEmpty()
		//		업로드된 첨부파일이 없으면 true를 반환한다.
		//	String getOriginalFileName()
		//		첨부파일명을 반환한다.
		//	byte[] getBytes()
		//		첨부파일의 내용을 byte[]배열로 반환한다.
		//	InputStream getInputStream()
		//		서버의 임시 디렉토리에 저장된 첨부파일을 읽어오는 스트림을 반환한다.
		//	String getContentType()
		//		첨부파일의 컨텐트 타입을 MIME형식으로 반환한다.
		//	long getSize()
		//		첨부파일의 길이를 반환한다.

		//첨부파일 처리하기 절차
		//1. xxxForm객체에서 첨부파일 정보를 가지고 있는 MultipartFile객체를 조회한다.
		MultipartFile upfile = boardForm.getUpfile();
		//2. 업로드된 첨부파일이 존재하는 지 확인한다.
		if (!upfile.isEmpty()) {
			//3. 첨부파일이 존재하면, 업로드된 첨부파일의 이름을 조회한다.
			String filename = upfile.getOriginalFilename();
			// 파일명의 중복을 피하기 위한 장치 (업로드 시간을 파일명 앞에 붙인다)
			//4. 파일이름 붕복으로 덮어쓰기가 발생하지 않도록 파일이름에 유닉스타입을 추가한다.
			filename = System.currentTimeMillis() + filename;
			//5. 업로드된 첨부파일을 저장하기 위한 파일 객체를 생성한다.
			File file = new File(saveDirectory, filename);
			//6. FileCopyUtils.copy(소스, 대상)를 사용해서 임시디렉토리에 저장된
			//	 첨부파일을 File객체가 지정한 위치로 복사한다.
			FileCopyUtils.copy(upfile.getInputStream(), new FileOutputStream(file));
			//7.파일명을 table에 저장하기 위해서 파일명을 VO객체에 추가한다.
			board.setFilename(filename);
		}
		
		boardService.addNewBoard(board);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/likes.do")
	public String likesBoard(@RequestParam("no") long boardNo, RedirectAttributes redirectAttributes) {
		boardService.increaseBoardLikes(boardNo);
		// 리다이렉트 할 때 보내주는 경로 뒤에 붙을 요청파라미터 이름과 값 설정
		redirectAttributes.addAttribute("no", boardNo);
		
		return "redirect:detail.do";
	}
	
	@RequestMapping("/detail.do")
	public String boardDetail(@RequestParam("no") long boardNo, Model model) {
		model.addAttribute("board", boardService.getBoardDetail(boardNo));
		
		return "board/detail";
	}
	
	@PostMapping("/delete.do")
	public String deleteBoard(@RequestParam("no") long boardNo, 
							  @RequestParam("password") String password) {
		boardService.deleteBoard(boardNo, password);
		
		return "redirect:list.do";
	}
	
	@GetMapping("/download.do")
	public ModelAndView downloadFile(@RequestParam("no") long boardNo) {
		ModelAndView mav = new ModelAndView();
		
		Board board = boardService.getBoardDetail(boardNo);
		if(board != null && board.getFilename() != null) {
			mav.addObject("directory",saveDirectory);
			mav.addObject("filename", board.getFilename());	//1596433246361Penguins.jpg
			mav.addObject("originalFileName", board.getOriginalFilename());	//Penguins.jpg
			mav.setView(fileDownloadView);
		}
		return mav;
	}
	
}