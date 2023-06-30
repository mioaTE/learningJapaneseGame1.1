BEGIN TRANSACTION;

DROP TABLE IF EXISTS question, genre CASCADE;

CREATE TABLE genre(
    genre_id int,
    genre_name varchar(200),

    CONSTRAINT PK_genre PRIMARY KEY(genre_id)
);

CREATE TABLE question(
    question_id int,
    question varchar(200),
    options varchar(400),
    answer int,
    genre_id int,

    CONSTRAINT PK_question PRIMARY KEY(question_id),
    CONSTRAINT FK_question FOREIGN KEY(genre_id) REFERENCES genre(genre_id)
);


INSERT INTO genre(genre_id, genre_name) VALUES (1, 'Hiragana');
INSERT INTO genre(genre_id, genre_name) VALUES (2, 'Katakana');
INSERT INTO genre(genre_id, genre_name) VALUES (3, 'Kanji');

INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (1, 'Good morning', '1) こんにちは　2) こんばんは　3) おはよう　4) さようなら', 3, 1);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (2, 'Good afternoon', '1) こんにちは　2) こんばんは　3) おはよう　4) さようなら', 1, 1);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (3, 'Good evening', '1) こんにちは　2) こんばんは　3) おはよう　4) さようなら', 2, 1);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (4, 'Good bye', '1) こんにちは　2) こんばんは　3) おはよう　4) さようなら', 4, 1);

INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (101, 'Laptop', '1) バス　2) ノートパソコン　3) ピアノ　4) テレビ', 2, 2);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (102, 'Bus', '1) バス　2) ノートパソコン　3) ピアノ　4) テレビ', 1, 2);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (103, 'Piano', '1) バス　2) ノートパソコン　3) ピアノ　4) テレビ', 3, 2);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (104, 'TV', '1) バス　2) ノートパソコン　3) ピアノ　4) テレビ', 4, 2);

INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (201, 'House', '1) 本　2) 教室　3) 学校　4) 家', 4, 3);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (202, 'School', '1) 本　2) 教室　3) 学校　4) 家', 3, 3);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (203, 'Class room', '1) 本　2) 教室　3) 学校　4) 家', 2, 3);
INSERT INTO question(question_id, question, options, answer, genre_id) VALUES (204, 'Book', '1) 本　2) 教室　3) 学校　4) 家', 1, 3);




ROLLBACK;
