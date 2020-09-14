DROP TABLE company_user_likes IF EXISTS;
DROP TABLE user_comp_likes IF EXISTS;
DROP TABLE user_user_likes IF EXISTS;
DROP TABLE community_contents_tags IF EXISTS;
DROP TABLE community_imgs IF EXISTS;
DROP TABLE community_writings IF EXISTS;
DROP TABLE community_files IF EXISTS;
DROP TABLE community_contents IF EXISTS;
DROP TABLE community_guests IF EXISTS;
DROP TABLE community_tags IF EXISTS;
DROP TABLE communities IF EXISTS;
DROP TABLE company_board_tags IF EXISTS;
DROP TABLE user_board_tags IF EXISTS;
DROP TABLE company_total_boards IF EXISTS;
DROP TABLE user_total_boards IF EXISTS;
DROP TABLE tags IF EXISTS;
DROP TABLE categories IF EXISTS;
DROP TABLE licenses IF EXISTS;
DROP TABLE careers IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE companies IF EXISTS;

/**********************************/
/* Table Name: 기업정보 */
/**********************************/
CREATE TABLE companies(
		company_no INTEGER NOT NULL IDENTITY,
		company_id VARCHAR(100) NOT NULL,
		company_password VARCHAR(50) NOT NULL,
		company_name VARCHAR(100) NOT NULL,
		company_content VARCHAR(500) DEFAULT 안녕하세요.,
		company_actived CHAR(1) DEFAULT Y,
		company_registered_date DATE DEFAULT sysdate,
		company_logo VARCHAR(100),
		company_url VARCHAR(1000) NOT NULL,
		company_size VARCHAR(10) DEFAULT '일반' NOT NULL,
		company_address VARCHAR(100) NOT NULL,
		company_income DOUBLE DEFAULT 0.0,
		company_started_date DATE
);

/**********************************/
/* Table Name: 회원정보 */
/**********************************/
CREATE TABLE users(
		user_no INTEGER NOT NULL IDENTITY,
		user_id VARCHAR(50) NOT NULL,
		login_way VARCHAR(500) NOT NULL,
		user_name VARCHAR(50),
		user_img VARCHAR(1000),
		user_birth DATE,
		user_git_addr VARCHAR(1000),
		user_email VARCHAR(256),
		user_deleted CHAR(1) DEFAULT N,
		user_actived CHAR(1) DEFAULT Y,
		user_gratuation VARCHAR(50),
		user_registered_date DATE DEFAULT sysdate
);

/**********************************/
/* Table Name: 경력 테이블 */
/**********************************/
CREATE TABLE careers(
		career_no INTEGER NOT NULL IDENTITY,
		career_company_name VARCHAR(100) NOT NULL,
		career_month INTEGER NOT NULL,
		career_job_content VARCHAR(500) NOT NULL,
		career_job_position VARCHAR(50) NOT NULL,
		user_no INTEGER
);

/**********************************/
/* Table Name: 자격정보 */
/**********************************/
CREATE TABLE licenses(
		license_no INTEGER NOT NULL IDENTITY,
		license_name VARCHAR(100) NOT NULL,
		license_grade VARCHAR(50),
		license_company VARCHAR(100) NOT NULL,
		license_registered_date DATE NOT NULL,
		user_no INTEGER
);

/**********************************/
/* Table Name: 카테고리 */
/**********************************/
CREATE TABLE categories(
		category_no INTEGER NOT NULL IDENTITY,
		category_name VARCHAR(50) NOT NULL
);

/**********************************/
/* Table Name: 태그모음 */
/**********************************/
CREATE TABLE tags(
		tag_no INTEGER NOT NULL IDENTITY,
		tag_name VARCHAR(10) NOT NULL,
		category_no INT
);

/**********************************/
/* Table Name: 회원 전체게시판 */
/**********************************/
CREATE TABLE user_total_boards(
		userboard_no INTEGER NOT NULL IDENTITY,
		userboard_title VARCHAR(300) NOT NULL,
		userboard_content VARCHAR_IGNORECASE(2000) NOT NULL,
		userboard_git VARCHAR(100),
		userboard_img VARCHAR(300) DEFAULT gray.jpg,
		userboard_actived CHAR(1) DEFAULT 'N',
		userboard_warnings INTEGER DEFAULT 0,
		userboard_comp_likes INTEGER DEFAULT 0,
		userboard_user_likes INT DEFAULT 0,
		userboard_registered_date DATE DEFAULT sysdate,
		user_no INTEGER
);

/**********************************/
/* Table Name: 기업 전체게시판 */
/**********************************/
CREATE TABLE company_total_boards(
		compboard_no INTEGER NOT NULL IDENTITY,
		comboard_title VARCHAR(300) NOT NULL,
		compboard_content VARCHAR(2000) NOT NULL,
		compboard_actived CHAR(1) DEFAULT 'N',
		compboard_warnings INTEGER DEFAULT 0,
		compboard_user_likes INTEGER DEFAULT 0,
		compboard_registered_date DATE DEFAULT sysdate,
		company_no INTEGER
);

/**********************************/
/* Table Name: 사용자 게시판 태그 */
/**********************************/
CREATE TABLE user_board_tags(
		usertag_no INTEGER NOT NULL IDENTITY,
		userboard_no INTEGER,
		tag_no INTEGER
);

/**********************************/
/* Table Name: 기업게시판 태그 */
/**********************************/
CREATE TABLE company_board_tags(
		comptag_no INTEGER NOT NULL IDENTITY,
		compboard_no INTEGER,
		tag_no INTEGER
);

/**********************************/
/* Table Name: 커뮤니티 정보 */
/**********************************/
CREATE TABLE communities(
		commu_no INTEGER NOT NULL IDENTITY,
		commu_limitnum INTEGER DEFAULT 8,
		commu_img VARCHAR(300) DEFAULT gray.jpg,
		commu_title VARCHAR(300) NOT NULL,
		user_no INTEGER,
		commu_registered_date DATE DEFAULT sysdate,
		commu_actived CHAR(1) DEFAULT 'N',
		commu_range VARCHAR(10) DEFAULT '전체'
);

/**********************************/
/* Table Name: 커뮤니티 태그 */
/**********************************/
CREATE TABLE community_tags(
		commtag_no INTEGER NOT NULL IDENTITY,
		tag_no INTEGER,
		commu_no INTEGER
);

/**********************************/
/* Table Name: 커뮤니티 참여자 */
/**********************************/
CREATE TABLE community_guests(
		commu_no INTEGER,
		user_no INTEGER,
		guest_grade VARCHAR(5) DEFAULT '일반' NOT NULL,
		guest_registered_date DATE DEFAULT sysdate,
		guest_actived_date DATE DEFAULT sysdate
);

/**********************************/
/* Table Name: 커뮤니티 컨텐츠 모음 */
/**********************************/
CREATE TABLE community_contents(
		contents_no INTEGER NOT NULL IDENTITY,
		contents_registered_date DATE DEFAULT sysdate,
		contents_updated_date DATE DEFAULT sysdate,
		contents_actived CHAR(1) DEFAULT 'N',
		commu_no INTEGER,
		user_no INTEGER
);

/**********************************/
/* Table Name: 커뮤니티 파일 */
/**********************************/
CREATE TABLE community_files(
		file_no INTEGER NOT NULL IDENTITY,
		file_name VARCHAR(2000) NOT NULL,
		contents_no INTEGER
);

/**********************************/
/* Table Name: 커뮤니티 내용(글) */
/**********************************/
CREATE TABLE community_writings(
		writing_no INTEGER NOT NULL IDENTITY,
		writing_talk VARCHAR(2000) NOT NULL,
		contents_no INTEGER
);

/**********************************/
/* Table Name: 커뮤니티 이미지 */
/**********************************/
CREATE TABLE community_imgs(
		img_no INTEGER NOT NULL IDENTITY,
		img_name VARCHAR(1000) NOT NULL,
		contents_no INTEGER
);

/**********************************/
/* Table Name: 커뮤니티 컨텐츠 태그 */
/**********************************/
CREATE TABLE community_contents_tags(
		contentstag_no INTEGER NOT NULL IDENTITY,
		contentstag_name VARCHAR(10) NOT NULL,
		contents_no INTEGER
);

/**********************************/
/* Table Name: 사용자 게시판 사용자 좋아요 */
/**********************************/
CREATE TABLE user_user_likes(
		userlike_no INT NOT NULL IDENTITY,
		userlike_yn CHAR(1) DEFAULT 'N',
		userboard_no INTEGER,
		user_no INTEGER
);

/**********************************/
/* Table Name: 사용자 게시판 기업 좋아요 */
/**********************************/
CREATE TABLE user_comp_likes(
		complike_no INT NOT NULL IDENTITY,
		complike_yn CHAR(1) DEFAULT 'N',
		userboard_no INT,
		company_no INT
);

/**********************************/
/* Table Name: 기업 게시판 사용자 좋아요 */
/**********************************/
CREATE TABLE company_user_likes(
		com_userlikes_no INT NOT NULL IDENTITY,
		com_userlikes_yn CHAR(1) DEFAULT 'N',
		compboard_no INTEGER,
		company_no INTEGER
);


ALTER TABLE companies ADD CONSTRAINT IDX_companies_PK PRIMARY KEY (company_no);

ALTER TABLE users ADD CONSTRAINT IDX_users_PK PRIMARY KEY (user_no);

ALTER TABLE careers ADD CONSTRAINT IDX_careers_PK PRIMARY KEY (career_no);
ALTER TABLE careers ADD CONSTRAINT IDX_careers_FK0 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE licenses ADD CONSTRAINT IDX_licenses_PK PRIMARY KEY (license_no);
ALTER TABLE licenses ADD CONSTRAINT IDX_licenses_FK0 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE categories ADD CONSTRAINT IDX_categories_PK PRIMARY KEY (category_no);

ALTER TABLE tags ADD CONSTRAINT IDX_tags_PK PRIMARY KEY (tag_no);
ALTER TABLE tags ADD CONSTRAINT IDX_tags_FK0 FOREIGN KEY (category_no) REFERENCES categories (category_no);

ALTER TABLE user_total_boards ADD CONSTRAINT IDX_user_total_boards_PK PRIMARY KEY (userboard_no);
ALTER TABLE user_total_boards ADD CONSTRAINT IDX_user_total_boards_FK0 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE company_total_boards ADD CONSTRAINT IDX_company_total_boards_PK PRIMARY KEY (compboard_no);
ALTER TABLE company_total_boards ADD CONSTRAINT IDX_company_total_boards_FK0 FOREIGN KEY (company_no) REFERENCES companies (company_no);

ALTER TABLE user_board_tags ADD CONSTRAINT IDX_user_board_tags_PK PRIMARY KEY (usertag_no);
ALTER TABLE user_board_tags ADD CONSTRAINT IDX_user_board_tags_FK0 FOREIGN KEY (userboard_no) REFERENCES user_total_boards (userboard_no);
ALTER TABLE user_board_tags ADD CONSTRAINT IDX_user_board_tags_FK1 FOREIGN KEY (tag_no) REFERENCES tags (tag_no);

ALTER TABLE company_board_tags ADD CONSTRAINT IDX_company_board_tags_PK PRIMARY KEY (comptag_no);
ALTER TABLE company_board_tags ADD CONSTRAINT IDX_company_board_tags_FK0 FOREIGN KEY (compboard_no) REFERENCES company_total_boards (compboard_no);
ALTER TABLE company_board_tags ADD CONSTRAINT IDX_company_board_tags_FK1 FOREIGN KEY (tag_no) REFERENCES tags (tag_no);

ALTER TABLE communities ADD CONSTRAINT IDX_communities_PK PRIMARY KEY (commu_no);
ALTER TABLE communities ADD CONSTRAINT IDX_communities_FK0 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE community_tags ADD CONSTRAINT IDX_community_tags_PK PRIMARY KEY (commtag_no);
ALTER TABLE community_tags ADD CONSTRAINT IDX_community_tags_FK0 FOREIGN KEY (tag_no) REFERENCES tags (tag_no);
ALTER TABLE community_tags ADD CONSTRAINT IDX_community_tags_FK1 FOREIGN KEY (commu_no) REFERENCES communities (commu_no);

ALTER TABLE community_guests ADD CONSTRAINT IDX_community_guests_PK PRIMARY KEY (commu_no, user_no);
ALTER TABLE community_guests ADD CONSTRAINT IDX_community_guests_FK0 FOREIGN KEY (commu_no) REFERENCES communities (commu_no);
ALTER TABLE community_guests ADD CONSTRAINT IDX_community_guests_FK1 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE community_contents ADD CONSTRAINT IDX_community_contents_PK PRIMARY KEY (contents_no);
ALTER TABLE community_contents ADD CONSTRAINT IDX_community_contents_FK0 FOREIGN KEY (commu_no,user_no) REFERENCES community_guests (commu_no,user_no);

ALTER TABLE community_files ADD CONSTRAINT IDX_community_files_PK PRIMARY KEY (file_no);
ALTER TABLE community_files ADD CONSTRAINT IDX_community_files_FK0 FOREIGN KEY (contents_no) REFERENCES community_contents (contents_no);

ALTER TABLE community_writings ADD CONSTRAINT IDX_community_writings_PK PRIMARY KEY (writing_no);
ALTER TABLE community_writings ADD CONSTRAINT IDX_community_writings_FK0 FOREIGN KEY (contents_no) REFERENCES community_contents (contents_no);

ALTER TABLE community_imgs ADD CONSTRAINT IDX_community_imgs_PK PRIMARY KEY (img_no);
ALTER TABLE community_imgs ADD CONSTRAINT IDX_community_imgs_FK0 FOREIGN KEY (contents_no) REFERENCES community_contents (contents_no);

ALTER TABLE community_contents_tags ADD CONSTRAINT IDX_community_contents_tags_PK PRIMARY KEY (contentstag_no);
ALTER TABLE community_contents_tags ADD CONSTRAINT IDX_community_contents_tags_FK0 FOREIGN KEY (contents_no) REFERENCES community_contents (contents_no);

ALTER TABLE user_user_likes ADD CONSTRAINT IDX_user_user_likes_PK PRIMARY KEY (userlike_no);
ALTER TABLE user_user_likes ADD CONSTRAINT IDX_user_user_likes_FK0 FOREIGN KEY (userboard_no) REFERENCES user_total_boards (userboard_no);
ALTER TABLE user_user_likes ADD CONSTRAINT IDX_user_user_likes_FK1 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE user_comp_likes ADD CONSTRAINT IDX_user_comp_likes_PK PRIMARY KEY (complike_no);
ALTER TABLE user_comp_likes ADD CONSTRAINT IDX_user_comp_likes_FK0 FOREIGN KEY (userboard_no) REFERENCES user_total_boards (userboard_no);
ALTER TABLE user_comp_likes ADD CONSTRAINT IDX_user_comp_likes_FK1 FOREIGN KEY (company_no) REFERENCES companies (company_no);

ALTER TABLE company_user_likes ADD CONSTRAINT IDX_company_user_likes_PK PRIMARY KEY (com_userlikes_no);
ALTER TABLE company_user_likes ADD CONSTRAINT IDX_company_user_likes_FK0 FOREIGN KEY (compboard_no) REFERENCES company_total_boards (compboard_no);
ALTER TABLE company_user_likes ADD CONSTRAINT IDX_company_user_likes_FK1 FOREIGN KEY (company_no) REFERENCES companies (company_no);

