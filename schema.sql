CREATE DATABASE IF NOT EXISTS password_vault;

USE password_vault;

-- ===========================
-- USERS TABLE
-- ===========================

CREATE TABLE users (

    id INT PRIMARY KEY AUTO_INCREMENT,

    username VARCHAR(100) NOT NULL UNIQUE,

    email VARCHAR(150) NOT NULL UNIQUE,

    master_password VARCHAR(255) NOT NULL

);

-- ===========================
-- CREDENTIALS TABLE
-- ===========================

CREATE TABLE credentials (

    id INT PRIMARY KEY AUTO_INCREMENT,

    user_id INT NOT NULL,

    website VARCHAR(150) NOT NULL,

    website_username VARCHAR(150) NOT NULL,

    encrypted_password TEXT NOT NULL,

    notes TEXT,

    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE

);

-- ===========================
-- LOGIN HISTORY TABLE
-- ===========================

CREATE TABLE login_history (

    id INT PRIMARY KEY AUTO_INCREMENT,

    user_id INT NOT NULL,

    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    status VARCHAR(20),

    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE

);