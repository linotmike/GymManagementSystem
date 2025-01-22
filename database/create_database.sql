DROP
DATABASE IF EXISTS gym;
CREATE
DATABASE gym;
USE
gym;

# ---------------------------------------------#
# Tables
# ---------------------------------------------#
CREATE TABLE users
(
    user_id  INT          NOT NULL AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     ENUM('admin', 'member', 'guest') NOT NULL,
    PRIMARY KEY (user_id)
);
CREATE TABLE pilates_pricing (
                                 pricing_id INT NOT NULL AUTO_INCREMENT,
                                 description VARCHAR(255),
                                 cost_for_members DECIMAL(10, 2),
                                 cost_for_non_members DECIMAL(10, 2),
                                 session_type ENUM('group', 'private'),
                                 frequency_info VARCHAR(255),
                                 PRIMARY KEY (pricing_id)
);
CREATE TABLE membership_types(
    membership_type_id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL,
    price_per_month DECIMAL(10,2),
    price_per_week DECIMAL(10,2),
    classes_per_week INT,
    PRIMARY KEY (membership_type_id)
);

CREATE TABLE members(
    member_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    membership_type_id INT NOT NULL ,
    pilates_pricing_id INT,
    start_date DATE NOT NULL,
    end_date DATE,
    status ENUM ('active','paused','expired') NOT NULL,
    PRIMARY KEY (member_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (membership_type_id) REFERENCES membership_types(membership_type_id),
    FOREIGN KEY (pilates_pricing_id) REFERENCES pilates_pricing(pricing_id)
);

CREATE TABLE classes
(
    class_id         INT          NOT NULL AUTO_INCREMENT,
    class_name       VARCHAR(50)  NOT NULL,
    class_type ENUM('mat', 'reformer', 'prenatal', 'postpartum') NOT NULL,
    description      VARCHAR(255) NOT NULL,
    difficulty_level ENUM('beginner', 'intermediate', 'advanced'),
    max_participants INT DEFAULT 0,
    PRIMARY KEY (class_id)


);

CREATE TABLE instructors
(
    instructor_id INT NOT NULL AUTO_INCREMENT,
    user_id       INT NOT NULL,
    first_name    VARCHAR(50),
    last_name     VARCHAR(50),
    bio           VARCHAR(255),
    specialty     VARCHAR(50),
    PRIMARY KEY (instructor_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE schedules
(
    schedule_id   INT NOT NULL AUTO_INCREMENT,
    class_id      INT NOT NULL,
    instructor_id INT NOT NULL,
    start_time    TIME,
    end_time      TIME,
    day_of_week   ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
    PRIMARY KEY (schedule_id),
    FOREIGN KEY (class_id) REFERENCES classes (class_id),
    FOREIGN KEY (instructor_id) REFERENCES instructors (instructor_id)
);

CREATE TABLE bookings
(
    booking_id   INT  NOT NULL AUTO_INCREMENT,
    class_id     INT  NOT NULL,
    member_id INT NOT NULL,
    user_id      INT  NOT NULL,
    schedule_id  INT  NOT NULL,
    booking_date DATE NOT NULL,
    status       ENUM('booked', 'cancelled', 'completed'),
    PRIMARY KEY (booking_id),
    FOREIGN KEY (class_id) REFERENCES classes (class_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (schedule_id) REFERENCES schedules (schedule_id)

);

CREATE TABLE profiles
(
    profile_id       INT          NOT NULL AUTO_INCREMENT,
    user_id          INT          NOT NULL,
    bio              VARCHAR(255),
    image_url        VARCHAR(255),
    phone            VARCHAR(20)  NOT NULL,
    email            VARCHAR(200) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    city             VARCHAR(255) NOT NULL,
    state            VARCHAR(20)  NOT NULL,
    zip              VARCHAR(20)  Not Null,
    date_of_birth    DATE         NOT NULL,
    fitness_goals    VARCHAR(255) NOT NULL,
    health_condition VARCHAR(50)  NOT NULL,
    PRIMARY KEY (profile_id),
    FOREIGN KEY (user_id) references users (user_id) ON DELETE CASCADE
);

CREATE TABLE categories
(
    category_id INT NOT NULL AUTO_INCREMENT,
    name        varchar(50),
    description TEXT,
    PRIMARY KEY (category_id)
);

CREATE TABLE products
(
    product_id     INT          NOT NULL AUTO_INCREMENT,
    product_name   VARCHAR(255) NOT NULL,
    price          DECIMAL(10, 2) DEFAULT 0,
    stock_quantity INT          NOT NULL,
    category_id    INT          NOT NULL,
    description    TEXT,
    color          VARCHAR(50),
    image          VARCHAR(255),
    PRIMARY KEY (product_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id)

);

CREATE TABLE shopping_cart
(
    shopping_cart_id INT  NOT NULL AUTO_INCREMENT,
    user_id          INT  NOT NULL,
    date_created     DATE NOT NULL,
    PRIMARY KEY (shopping_cart_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE shopping_cart_items
(
    shopping_cart_items_id INT NOT NULL AUTO_INCREMENT,
    shopping_cart_id       INT NOT NULL,
    product_id             INT NOT NULL,
    quantity               INT NOT NULL   DEFAULT 1,
    price                  DECIMAL(10, 2) DEFAULT 0,
    PRIMARY KEY (shopping_cart_items_id),
    FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (shopping_cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (product_id)

);

CREATE TABLE orders
(
    order_id               INT  NOT NULL AUTO_INCREMENT,
    user_id                INT  NOT NULL,
    shopping_cart_items_id INT  NOT NULL,
    order_date             DATE NOT NULL,
    total_price            DECIMAL(10, 2) DEFAULT 0,
    status                 ENUM('placed', 'shipped', 'delivered', 'cancelled'),
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (shopping_cart_items_id) REFERENCES shopping_cart_items (shopping_cart_items_id)

);

CREATE TABLE order_items
(
    order_items_id INT NOT NULL AUTO_INCREMENT,
    order_id       INT NOT NULL,
    product_id     INT NOT NULL,
    quantity       INT NOT NULL,
    price          DECIMAL(10, 2) DEFAULT 0,
    PRIMARY KEY (order_items_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);
CREATE INDEX idx_day_of_week ON schedules (day_of_week);
ALTER TABLE instructors
    ADD CONSTRAINT unique_user_id UNIQUE (user_id);


-- Insert data into `users`

INSERT INTO users (username, email, password, role)
VALUES
    ('alice', 'alice@example.com', '$2a$10$NkufUPF3V8dEPSZeo1fzHe9ScBu.LOay9S3N32M84yuUM2OJYEJ/.', 'MEMBER'),
    ('lino', 'bob@example.com', '$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2', 'ADMIN'),
    ('charlie', 'charlie@example.com', '$2a$10$NkufUPF3V8dEPSZeo1fzHe9ScBu.LOay9S3N32M84yuUM2OJYEJ/.', 'GUEST'),
    ('diana', 'diana@example.com', '$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2', 'MEMBER');

-- Insert data into `pilates_pricing`
INSERT INTO pilates_pricing (description, cost_for_members, cost_for_non_members, session_type, frequency_info)
VALUES
    ('One Pilates class monthly for free', 0.00, 45.99, 'group', '1 class per month'),
    ('Pilates Plus Gold', 400.00, 45.99, 'group', '2 classes per week'),
    ('Pilates Premium', 340.00, 90.00, 'group', '3 classes per week');
-- Insert data into `membership_types `
    INSERT INTO membership_types ( type, price_per_month, price_per_week, classes_per_week)
    VALUES
        ('Basic', 100.00, 25.00, 2),
        ('Premium', 340.00, 90.00, 12),
        ('VIP', 500.00, 125.00, 20);
-- Insert data into `members`
INSERT INTO members (user_id, membership_type_id, pilates_pricing_id, start_date, end_date, status)
VALUES
    (1, 1, 1, '2023-01-01', NULL, 'active'),
    (2, 2, 2, '2023-01-02', NULL, 'active'),
    (3, 3, 3, '2023-01-03', NULL, 'paused'),
    (4, 1, 1, '2023-01-04', '2023-06-04', 'expired');



-- Insert data into `classes`
INSERT INTO classes (class_name, class_type, description, difficulty_level, max_participants)
VALUES
    ('Yoga', 'mat', 'A relaxing class focused on stretching and mindfulness.', 'beginner', 10),
    ('Spin', 'reformer', 'A high intensity bike class to get your heart racing.', 'advanced', 8),
    ('Prenatal Pilates', 'prenatal', 'Pilates class tailored for prenatal care.', 'intermediate', 5),
    ('Postpartum Pilates', 'postpartum', 'Pilates class tailored for postpartum recovery.', 'intermediate', 5);

-- Insert data into `instructors`
INSERT INTO instructors (user_id, first_name, last_name, bio, specialty)
VALUES
    (1, 'Alice', 'Johnson', 'Dedicated yoga instructor with a calm demeanor.', 'Yoga'),
    (2, 'Bob', 'Smith', 'A seasoned cyclist with over 20 years of experience.', 'Spin'),
    (3, 'Charlie', 'Brown', 'Expert in pilates with a focus on core and flexibility.', 'Pilates'),
    (4, 'Diana', 'Ross', 'Zumba instructor with a passion for dance and fitness.', 'Zumba');

-- Insert data into `schedules`
INSERT INTO schedules (class_id, instructor_id, start_time, end_time, day_of_week)
VALUES
    (1, 1, '08:00:00', '09:00:00', 'Monday'),
    (2, 2, '18:00:00', '19:00:00', 'Wednesday'),
    (3, 3, '10:00:00', '11:00:00', 'Friday'),
    (4, 4, '12:00:00', '13:00:00', 'Saturday');

-- Insert data into `bookings`
INSERT INTO bookings (class_id, member_id, user_id, schedule_id, booking_date, status)
VALUES
    (1, 1, 1, 1, '2023-12-01', 'booked'),
    (2, 2, 2, 2, '2023-12-03', 'cancelled'),
    (3, 3, 3, 3, '2023-12-05', 'completed'),
    (4, 4, 4, 4, '2023-12-07', 'booked');

-- Insert data into `profiles`
INSERT INTO profiles (user_id, bio, image_url, phone, email, address, city, state, zip, date_of_birth, fitness_goals, health_condition)
VALUES
    (1, 'Passionate about fitness.', 'http://example.com/image1.jpg', '555-1234', 'alice@example.com', '123 Elm St', 'Townsville', 'CA', '90001', '1990-05-01', 'Increase flexibility', 'None'),
    (2, 'Loves cycling and outdoor activities.', 'http://example.com/image2.jpg', '555-5678', 'bob@example.com', '456 Maple St', 'Springfield', 'IL', '62704', '1988-08-15', 'Win a triathlon', 'None'),
    (3, 'Enjoys pilates and long walks.', 'http://example.com/image3.jpg', '555-8765', 'charlie@example.com', '789 Oak St', 'Smallville', 'KS', '66002', '1985-02-20', 'Core strengthening', 'Low back pain'),
    (4, 'Zumba enthusiast with a flair for dancing.', 'http://example.com/image4.jpg', '555-4321', 'diana@example.com', '101 Cedar St', 'Hill Valley', 'CA', '93003', '1970-07-07', 'Stay active', 'Arthritis');

-- Insert data into `categories`
INSERT INTO categories (name, description)
VALUES
    ('Nutrition', 'Health supplements and nutritional products'),
    ('Apparel', 'Workout and fitness apparel, including shorts, shirts, and leggings'),
    ('Equipment', 'Home and gym fitness equipment'),
    ('Accessories', 'Fitness-related accessories like bands, mats, and water bottles');

-- Insert data into `products`
INSERT INTO products (product_name, description, price, stock_quantity, category_id, color, image)
VALUES
    ('Energy Bar', 'Nutrient-packed energy bar for quick boosts.', 2.50, 200, 1, 'N/A', 'http://example.com/energybar.jpg'),
    ('Fitness Shirt', 'A lightweight shirt for workouts.', 25.00, 50, 2, 'Blue', 'http://example.com/shirt.jpg'),
    ('Yoga Mat', 'High-quality yoga mat for all levels of yoga practitioners.', 50.00, 30, 3, 'Green', 'http://example.com/mat.jpg'),
    ('Water Bottle', 'Durable water bottle for hydration during workouts.', 15.00, 150, 4, 'Clear', 'http://example.com/bottle.jpg');

-- Insert data into `shopping_cart`
INSERT INTO shopping_cart (user_id, date_created)
VALUES
    (1, '2023-11-10'),
    (2, '2023-11-11'),
    (3, '2023-11-12'),
    (4, '2023-11-13');

-- Insert data into `shopping_cart_items`
INSERT INTO shopping_cart_items (shopping_cart_id, product_id, quantity, price)
VALUES
    (1, 1, 2, 2.50),
    (2, 2, 1, 25.00),
    (3, 3, 1, 50.00),
    (4, 4, 3, 15.00);

-- Insert data into `orders`
INSERT INTO orders (user_id, shopping_cart_items_id, order_date, total_price, status)
VALUES
    (1, 1, '2023-11-14', 5.00, 'placed'),
    (2, 2, '2023-11-15', 25.00, 'shipped'),
    (3, 3, '2023-11-16', 50.00, 'delivered'),
    (4, 4, '2023-11-17', 45.00, 'cancelled');

-- Insert data into `order_items`
INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES
    (1, 1, 2, 2.50),
    (2, 2, 1, 25.00),
    (3, 3, 1, 50.00),
    (4, 4, 3, 15.00);


