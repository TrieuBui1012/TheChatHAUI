CREATE TABLE students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_code INTEGER UNIQUE,
    password TEXT,
    fullname TEXT,
    avatar TEXT,
    height REAL,
    weight REAL,
    underlying_disease TEXT,
    gender INTEGER,
    dob TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO students (student_code, password, fullname, avatar, height, weight, underlying_disease, gender, dob)
VALUES ('2021600001', '2021600001', 'Nguyễn Văn A', '2021600001.png', 1.7, 65, 'Không', 1, '2003/01/01'),
    ('2021600002', '2021600002', 'Nguyễn Thị B', '2021600002.png', 1.7, 40, 'Nhóm 1: Rối loạn chuyển hoá', 0, '2003/02/01'),
    ('2021600003', '2021600003', 'Nguyễn Văn C', '2021600003.png', 1.7, 85, 'Nhóm 2: Hô hấp', 1, '2003/03/01'),
    ('2021600004', '2021600004', 'Trần Thị D', '2021600004.png', 1.7, 100, 'Nhóm 3: Tim mạch', 0, '2003/04/01'),
    ('2021600005', '2021600005', 'Trần Văn E', '2021600005.png', 1.7, 120, 'Suy thận', 1, '2003/05/01');

CREATE TABLE courses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    course_code TEXT UNIQUE,
    course_name TEXT,
    is_physical INTEGER,
    credits REAL,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO courses (course_code, course_name, is_physical, credits)
VALUES ('FL6085', 'Tiếng Anh Công nghệ thông tin cơ bản 1', 0, 5.0),
    ('FL6086', 'Tiếng Anh Công nghệ thông tin cơ bản 2', 0, 5.0),
    ('FL6087', 'Tiếng Anh Công nghệ thông tin cơ bản 3', 0, 5.0),
    ('FL6088', 'Tiếng Anh Công nghệ thông tin cơ bản 4', 0, 5.0),
    ('LP6010', 'Triết học Mác-Lênin', 0, 3.0),
    ('LP6011', 'Kinh tế chính trị Mác-Lênin', 0, 2.0),
    ('LP6012', 'Chủ nghĩa xã hội khoa học', 0, 2.0),
    ('LP6013', 'Lịch sử Đảng Cộng sản Việt Nam', 0, 2.0),
    ('LP6004', 'Tư tưởng Hồ Chí Minh', 0, 2.0),
    ('BS6018', 'Giao tiếp liên văn hóa', 0, 2.0),
    ('LP6003', 'Pháp luật đại cương', 0, 2.0),
    ('FL6343', 'Tiếng Anh Công nghệ thông tin 1', 0, 5.0),
    ('FL6344', 'Tiếng Anh Công nghệ thông tin 2', 0, 5.0),
    ('BS6019', 'Nhập môn nghiên cứu khoa học', 0, 2.0),
    ('BS6024', 'Mỹ thuật đại cương', 0, 2.0),
    ('BS6002', 'Giải tích', 0, 3.0),
    ('BS6001', 'Đại số tuyến tính', 0, 3.0),
    ('IT6016', 'Kỹ thuật số', 0, 3.0),
    ('BS6027', 'Vật lý đại cương', 0, 3.0),
    ('IT6035', 'Toán rời rạc', 0, 3.0),
    ('BS6008', 'Xác suất thống kê', 0, 3.0),
    ('PE6001', 'Aerobic 1', 1, 1.0),
    ('PE6002', 'Aerobic 2', 1, 1.0),
    ('PE6005', 'Bơi 1', 1, 1.0),
    ('PE6006', 'Bơi 2', 1, 1.0),
    ('PE6017', 'Bóng bàn 1', 1, 1.0),
    ('PE6018', 'Bóng bàn 2', 1, 1.0),
    ('PE6003', 'Bóng chuyền 1', 1, 1.0),
    ('PE6004', 'Bóng chuyền 2', 1, 1.0),
    ('PE6027', 'Bóng đá 1', 1, 1.0),
    ('PE6028', 'Bóng đá 2', 1, 1.0),
    ('PE6021', 'Bóng rổ 1', 1, 1.0),
    ('PE6022', 'Bóng rổ 2', 1, 1.0),
    ('PE6025', 'Cầu lông 1', 1, 1.0),
    ('PE6026', 'Cầu lông 2', 1, 1.0),
    ('PE6031', 'Cầu mây 1', 1, 1.0),
    ('PE6032', 'Cầu mây 2', 1, 1.0),
    ('PE6029', 'Đá cầu 1', 1, 1.0),
    ('PE6030', 'Đá cầu 2', 1, 1.0),
    ('PE6011', 'Karate 1', 1, 1.0),
    ('PE6012', 'Karate 2', 1, 1.0),
    ('PE6013', 'Khiêu vũ 1', 1, 1.0),
    ('PE6014', 'Khiêu vũ 2', 1, 1.0),
    ('PE6015', 'Pencak Silat 1', 1, 1.0),
    ('PE6016', 'Pencak Silat 2', 1, 1.0),
    ('PE6019', 'Tennis 1', 1, 1.0),
    ('PE6020', 'Tennis 2', 1, 1.0),
    ('DC6005', 'Công tác quốc phòng và an ninh', 0, 2.0),
    ('DC6004', 'Đường lối QP&AN của ĐCS Việt Nam', 0, 3.0),
    ('DC6007', 'Kỹ thuật chiến đấu bộ binh và chiến thuật', 0, 2.0),
    ('DC6006', 'Quân sự chung', 0, 1.5),
    ('IT6011', 'Nhập môn về kỹ thuật', 0, 2.0),
    ('IT6015', 'Kỹ thuật lập trình', 0, 3.0),
    ('IT6126', 'Hệ thống cơ sở dữ liệu', 0, 4.0),
    ('IT6067', 'Kiến trúc máy tính và hệ điều hành', 0, 3.0),
    ('IT6120', 'Lập trình hướng đối tượng', 0, 3.0),
    ('IT6001', 'An toàn và bảo mật thông tin', 0, 3.0),
    ('IT6002', 'Cấu trúc dữ liệu và giải thuật', 0, 3.0),
    ('IT6083', 'Mạng máy tính', 0, 3.0),
    ('IT6082', 'Nhập môn công nghệ phần mềm', 0, 3.0),
    ('IT6066', 'Phân tích thiết kế phần mềm', 0, 3.0),
    ('IT6071', 'Phát triển dự án công nghệ thông tin', 0, 3.0),
    ('IT6100', 'Thiết kế đồ hoạ 2D', 0, 3.0),
    ('IT6039', 'Thiết kế Web', 0, 3.0),
    ('IT6121', 'Thực tập cơ sở ngành', 0, 3.0),
    ('IT6043', 'Trí tuệ nhân tạo', 0, 3.0),
    ('IT6056', 'Quản trị mạng trên hệ điều hành Windows', 0, 3.0),
    ('IT6123', 'Tương tác người máy', 0, 3.0),
    ('IT6122', 'Đồ án chuyên ngành', 0, 3.0),
    ('IT6013', 'Kiểm thử phần mềm', 0, 3.0),
    ('IT6029', 'Phát triển ứng dụng trên thiết bị di động', 0, 3.0),
    ('IT6034', 'Tích hợp hệ thống phần mềm', 0, 3.0),
    ('IT6129', 'Đồ án tốt nghiệp', 0, 9.0),
    ('IT6128', 'Thực tập doanh nghiệp', 0, 6.0);

CREATE TABLE instructors (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    instructor_code INTEGER UNIQUE,
    fullname TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO instructors (instructor_code, fullname)
VALUES ('111', 'Đỗ Thị F'),
    ('222', 'Nhữ Thục G'),
    ('333', 'Dương Việt H'),
    ('444', 'Hoàng Việt I'),
    ('555', 'Đỗ Thành K');

CREATE TABLE classes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    class_code TEXT UNIQUE,
    days_in_week TEXT,
    time_in_day TEXT,
    started_at TEXT,
    finished_at TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    course_id INTEGER NOT NULL,
    instructor_id INTEGER NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(id)
);

INSERT INTO classes (class_code, days_in_week, time_in_day, started_at, finished_at, course_id, instructor_id)
VALUES ('20211IT6029001', '5', '7,8,9', '2023-09-16', '2023-11-11', 71, 1),
    ('20211PE6027001', '1,3', '1,2', '2023-09-16', '2023-11-01', 30, 2),
    ('20211IT6039001', '2', '1,2,3', '2023-09-16', '2023-12-31', 64, 3),
    ('20211IT6043001', '4', '7,8,9', '2023-09-13', '2023-12-31', 66, 4),
    ('20211IT6071001', '0', '4,5,6', '2023-09-15', '2023-12-31', 62, 5);

CREATE TABLE trackers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    is_enough_water INTEGER,
    liter REAL,
    is_doing_exercise INTEGER,
    exercise_span INTEGER,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    student_id INTEGER NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id)
);

INSERT INTO trackers (is_enough_water, liter, is_doing_exercise, exercise_span, student_id, created_at)
VALUES (1, 2.4, 1, 30, 1, '2023-12-01 00:00:00'),
    (0, 2.4, 0, 0, 1, '2023-12-02 00:00:00'),
    (1, 2.4, 1, 15, 1, '2023-12-03 00:00:00'),
    (1, 2.4, 1, 30, 1, '2023-12-04 00:00:00'),
    ( 0, 2.4, 0, 0, 1, '2023-12-05 00:00:00');

CREATE TABLE healths (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    BMI REAL,
    disease TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    student_id INTEGER NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id)
);

INSERT INTO healths (BMI, disease, student_id, created_at)
VALUES (22.5, 'Sốt xuất huyết', 1, '2023-12-01 00:00:00'),
    (22.5, 'Sốt xuất huyết', 1, '2023-12-02 00:00:00'),
    (22.5, 'Sốt xuất huyết', 1, '2023-12-03 00:00:00'),
    (22.5, null, 1, '2023-12-04 00:00:00'),
    (22.5, null, 1, '2023-12-05 00:00:00');

CREATE TABLE results (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    status INTEGER,
    exam_score REAL,
    midterm_score REAL,
    test1 REAL,
    test2 REAL,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    student_id INTEGER NOT NULL,
    class_id INTEGER NOT NULL,
    FOREIGN KEY (class_id) REFERENCES classes(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
);

INSERT INTO results(status, exam_score, midterm_score, test1, test2, student_id, class_id)
VALUES (1, 10.0, 10.0, 10.0, 10.0, 1, 1),
    (1, 10.0, 10.0, 10.0, 10.0, 1, 2),
    (null, null, null, null, null, 1, 3),
    (null, null, null, null, null, 1, 4),
    (null, null, null, null, null, 1, 5);

CREATE TABLE onleaves (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    status INTEGER,
    reason TEXT,
    date TEXT,
    created_at TEXT,
    updated_at TEXT,
    class_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    FOREIGN KEY (class_id) REFERENCES classes(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
);
