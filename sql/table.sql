-- 如果不存在名为 user 的表则创建表
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- 为 username 添加唯一索引（如果尚未存在）
CREATE UNIQUE INDEX  idx_username ON user(username);

-- 如果不存在 username 为 test 的数据，则插入
INSERT INTO user (username, password)
SELECT 'test', '123456'
    WHERE NOT EXISTS (
    SELECT 1 FROM user WHERE username = 'test'
);