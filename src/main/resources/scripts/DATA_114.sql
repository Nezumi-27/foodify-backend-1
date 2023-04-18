-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: foodify-backend
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `ward` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (5,'25 Ông Ích Khiêm','Quận Hải Châu','Phường Hải Châu I'),(6,'73 Nguyễn Văn Thoại','Quận Ngũ Hành Sơn','Phường Hoà Quý'),(7,'15/7 Ngũ Hành Sơn','Quận Ngũ Hành Sơn','Phường Hoà Quý'),(8,'K13/27 Bắc Sơn','Quận Cẩm Lệ','Phường Hòa Thọ Đông'),(10,'K17 FPT','Huyện Hoà Vang','Xã Hòa Liên'),(11,'K172 Ông Ích Khiêm','Quận Hải Châu','Phường Bình Hiên'),(12,'K173 Ông Ích Khiêm','Quận Hải Châu','Phường Hòa Cường Bắc'),(14,'182 Nguyễn Văn Thoại','Quận Ngũ Hành Sơn','Phường Hoà Quý'),(19,'195 Nguyễn Văn Thoại','Quận Ngũ Hành Sơn','Phường Mỹ An'),(20,'198 Nguyễn Văn Thoại','Quận Ngũ Hành Sơn','Phường Hoà Quý'),(25,'198 Nguyễn Văn Thoại','Huyện Hoà Vang','Xã Hòa Phong'),(31,'K536 Điện Biên Phủ','Quận Thanh Khê','Phường Thanh Khê Đông'),(32,'K536 Điện Biên Phủ','Quận Thanh Khê','Phường Thanh Khê Tây'),(34,'52 Trường Chinh','Quận Cẩm Lệ','Phường Hòa An'),(37,'112 Hoàng Sa','Quận Sơn Trà','Phường Nại Hiên Đông'),(39,'201 Hoà Hiên','Huyện Hoà Vang','Xã Hòa Phong'),(40,'512 Bàu Làng','Quận Thanh Khê','Phường Tam Thuận'),(43,'70 Hàm Nghi','Quận Hải Châu','Phường Hải Châu I'),(44,'70 Nguyễn Văn Linh','Quận Hải Châu','Phường Hải Châu II'),(50,'137 Phan Văn Định','Quận Liên Chiểu','Phường Hòa Khánh Bắc'),(51,'162 Hàm Nghi','Quận Thanh Khê','Phường Thạc Gián'),(52,'555 Điện Biên Phủ','Quận Thanh Khê','Phường Thanh Khê Đông'),(53,'112 Hoàng Sa','Quận Sơn Trà','Phường An Hải Tây'),(54,'88 Quảng Nam','Quận Cẩm Lệ','Xã Hòa Phong'),(55,'812 Trần Cao Vân','Quận Thanh Khê','Phường Thanh Khê Tây'),(56,'814 Trần Cao Vân','Quận Thanh Khê','Phường Thanh Khê Tây'),(57,'193 Nguyễn Lương Bằng','Quận Liên Chiểu','Phường Hòa Khánh Bắc'),(58,'190 Nam Cao','Quận Liên Chiểu','Phường Hòa Khánh Nam'),(60,'H09/7 K512 Điện Biên Phủ','Quận Thanh Khê','Phường Thanh Khê Đông'),(61,'166 Hàm Nghi','Quận Thanh Khê','Phường Thạc Gián'),(64,'Khu K, Khu đô thị FBT','Quận Ngũ Hành Sơn','Phường Hoà Hải'),(65,'H09/7 K536 Điện Biên Phủ','Quận Thanh Khê','Phường Thanh Khê Đông'),(66,'186 Phan Đăng Lưu','Quận Hải Châu','Phường Hòa Cường Bắc'),(67,'184 Phan Đăng Lưu','Quận Hải Châu','Phường Hòa Cường Bắc'),(69,'194 Phan Đăng Lưu','Quận Hải Châu','Phường Hòa Cường Bắc'),(70,'478 Điện Biên Phủ','Quận Thanh Khê','Phường Thanh Khê Đông'),(71,'814 Trần Cao Vân','Quận Thanh Khê','Phường Thanh Khê Đông');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKt8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1679539669505?alt=media&token=cd5e8b62-b654-42c0-a8a1-0a604ef84482','Bánh kem'),(2,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1679540088454?alt=media&token=a75f4894-cddb-4999-8d88-3f2dd5143e74','Bánh tráng'),(3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1679540831542?alt=media&token=dc93a255-ec69-49b1-b907-138e9cf867fc','Đồ ăn nhanh'),(4,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1679541536296?alt=media&token=9be579c1-60d4-4115-a84c-c89163c6424b','Gà'),(5,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1679540577079?alt=media&token=b4cc57f5-31e9-4133-ac2d-d7a211eca572','Bò'),(6,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Đồ nướng'),(7,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Trà sữa'),(8,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Kem'),(9,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1680248884790?alt=media&token=942cccf3-8322-4c19-9599-851e4bf310a2','Đồ sấy khô'),(10,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2F1680248862133?alt=media&token=b7a7ab8f-6edb-423c-8411-471c29d81b9c','Trà'),(13,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Bánh su'),(17,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Đồ khô'),(18,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Handmade'),(19,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Bánh mỳ'),(20,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Bánh ngọt'),(21,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Rượu'),(22,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Nước ép trái cây'),(23,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Category%2Fupdating.png?alt=media&token=a64a5b4d-76cb-48a3-a3db-64fa37c712c8','Ăn vặt');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `rating` float NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6uv0qku8gsu6x1r2jkrtqwjtn` (`product_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK6uv0qku8gsu6x1r2jkrtqwjtn` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (12,'Ngon vãi',5,2,2),(13,'Ngon quá',4.5,2,3),(14,'Sản phẩm này tuyệt quá',4.5,3,2);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `districts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES (1,'Huyện Hoà Vang'),(2,'Huyện Hoàng Sa'),(3,'Quận Cẩm Lệ'),(4,'Quận Hải Châu'),(5,'Quận Liên Chiểu'),(6,'Quận Ngũ Hành Sơn'),(7,'Quận Sơn Trà'),(8,'Quận Thanh Khê');
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `sub_total` bigint NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (4,2,190000,16,3),(5,1,95000,17,3),(6,2,30000,17,6),(11,2,190000,19,2),(12,3,140250,19,3),(13,3,60000,20,20),(14,4,80000,20,21),(15,3,82500,22,10),(16,4,120000,22,11),(17,5,475000,23,2),(18,5,475000,24,2),(28,3,285000,35,2),(33,2,190000,40,2);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_time` datetime(6) DEFAULT NULL,
  `order_tracking_number` varchar(255) NOT NULL,
  `payment_method` varchar(255) NOT NULL,
  `product_cost` bigint NOT NULL,
  `shipping_cost` bigint NOT NULL,
  `status` varchar(255) NOT NULL,
  `total` bigint NOT NULL,
  `shipper_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKokwrsbrir8wa5luxqkrjqr53x` (`order_tracking_number`),
  KEY `FKsk2tyu7xrdu2ienuay5yrpgoe` (`shipper_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsk2tyu7xrdu2ienuay5yrpgoe` FOREIGN KEY (`shipper_id`) REFERENCES `shippers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (16,'2023-03-22 10:06:00.909000','1234-4321','ZALO PAY',190000,20000,'COMPLETED',210000,3,2,'193 Nguyễn Lương Bằng, Hoà Khánh Bắc, Liên Chiểu, Đà Nẵng','1','1'),(17,'2023-03-22 10:10:38.559000','920192012','CASH',125000,20000,'COMPLETED',145000,3,3,'193 Nguyễn Lương Bằng, Hoà Khánh Bắc, Liên Chiểu, Đà Nẵng','1','1'),(19,'2023-04-01 17:54:34.752000','1111-2222-3333','ZALO PAY',330250,20000,'COMPLETED',350250,1,4,'K536 Điện Biên Phủ, Thanh Khê Đông, Thanh Khê, Đà Nẵng','1.0','1.0'),(20,'2023-04-05 17:30:31.958000','11-22-33-44','ZALO PAY',140000,15000,'AWAITING',155000,NULL,62,'K536 Điện Biên Phủ, Thanh Khê Đông, Thanh Khê, Đà Nẵng','1.0','1.0'),(22,'2023-04-05 17:31:27.311000','1234-5555-6666','ZALO PAY',202500,15000,'AWAITING',217500,NULL,62,'K536 Điện Biên Phủ, Thanh Khê Đông, Thanh Khê, Đà Nẵng','1.0','1.0'),(23,'2023-04-05 18:33:25.423000','1121-9200-1966','CASH',475000,20000,'COMPLETED',495000,3,4,'193 Nguyễn Lương Bằng, Hoà Khánh Bắc, Liên Chiểu, Đà Nẵng','1.0','1.0'),(24,'2023-04-05 22:42:06.428000','12344321','CASH',475000,20000,'COMPLETED',495000,3,2,'K152 Phạm Như Xương, Hoà Khánh Nam, Liên Chiểu, Đà Nẵng','1','1'),(35,'2023-04-06 11:36:27.480000','1234-4321-1234','ZALO PAY',285000,20000,'CONFIRMED',305000,2,2,'K536 Điện Biên Phủ, Thanh Khê Đông, Thanh Khê','1','1'),(40,'2023-04-07 22:15:30.186000','1903-3004-1975','ZALO PAY',190000,20000,'CONFIRMED',210000,1,2,'152 Phạm Như Xương, Hoà Khánh Nam, Liên Chiểu, Đà Nẵng','1.0','1.0');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `product_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `FKd112rx0alycddsms029iifrih` (`category_id`),
  CONSTRAINT `FKd112rx0alycddsms029iifrih` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKlda9rad6s180ha3dl1ncsp8n7` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (17,1),(18,1),(53,1),(19,2),(20,2),(21,2),(22,2),(2,3),(3,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(3,4),(9,4),(10,4),(11,4),(2,5),(2,6),(12,7),(13,7),(5,8),(6,8),(7,8),(8,8),(17,8),(18,8),(4,9),(14,10),(15,10),(16,10),(52,19),(52,20),(54,21),(54,22);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnq71xsohugpqwf3c9gxmsuy` (`product_id`),
  CONSTRAINT `FKqnq71xsohugpqwf3c9gxmsuy` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fbokho.jpg?alt=media&token=ce68ab6d-6fc5-47e2-9586-57fdfa1a3f55',2),(4,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fgakho.jpg?alt=media&token=e2b05a78-5a83-44f3-8a64-566bf0bf2650',3),(5,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',4),(6,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',5),(7,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',6),(8,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',7),(9,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',8),(10,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',8),(11,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',9),(12,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',10),(13,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',11),(14,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',12),(15,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',13),(16,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',14),(17,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',15),(18,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',16),(19,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',17),(20,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',18),(21,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',19),(22,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',20),(23,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',21),(24,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',22),(44,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680248744310?alt=media&token=4bf183ef-9453-4539-9469-0cc0c203b649',2),(47,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680258503111?alt=media&token=d6ca0738-3601-4925-9274-c11be6e4dcf9',9),(50,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680488969081?alt=media&token=b79c76b2-e1e8-4013-82a1-ff53cb826681',52),(51,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680488969077?alt=media&token=39e0efc5-5771-48be-9c84-55ca8561a635',52),(52,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680754727544?alt=media&token=f06c7157-94ad-4747-9ca7-fcee9e879501',53),(53,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680754971389?alt=media&token=748e12a6-ed30-417f-9050-9780c7fb3bdc',54),(54,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Products%2F1680754971366?alt=media&token=8dbdd115-84ba-49b4-a305-aa46e084bb4c',54);
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `average_rating` float DEFAULT NULL,
  `cost` bigint NOT NULL,
  `created_time` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_percent` float DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `review_count` int DEFAULT NULL,
  `shop_id` bigint DEFAULT NULL,
  `sold` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7kp8sbhxboponhx3lxqtmkcoj` (`shop_id`),
  CONSTRAINT `FK7kp8sbhxboponhx3lxqtmkcoj` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,4.75,95000,'2023-03-10 12:19:28.227000','Thịt bò khô ngũ vị mô tả',0,_binary '','Thịt bò khô ngũ vị',2,1,17),(3,4.5,55000,'2023-03-10 12:20:30.041000','Thịt gà sấy AK-47 ngon nhất hệ mặt trời',15,_binary '','Thịt gà sấy ak-47',1,1,9),(4,0,30000,'2023-03-10 12:22:02.836000','Rau củ sấy bằng Ulti Lucian, Senna vô sấy chung',0,_binary '','Rau củ sấy bằng ulti lucian',0,1,0),(5,0,10000,'2023-03-10 12:23:14.287000','Kem cây siêu rẻ',0,_binary '','Kem cây',0,2,0),(6,0,15000,'2023-03-10 12:23:28.066000','Kem cây siêu rẻ',0,_binary '','Kem cây socola',0,2,2),(7,0,25000,'2023-03-10 12:23:54.059000','Super Yasuo Ultimate',0,_binary '','Super Yasuo',0,2,0),(8,0,30000,'2023-03-10 12:24:13.883000','Super Yasuo Ultimate Sundae siêu ngon',0,_binary '','Super Yasuo Sundae',0,2,0),(9,0,27500,'2023-03-10 12:25:07.699000','Đùi gà rán chiên giòn',0,_binary '','Đùi gà rán',0,3,0),(10,0,27500,'2023-03-10 12:25:18.980000','Đùi gà rán chiên giòn siêu cay',0,_binary '','Đùi gà rán siêu cay',0,3,0),(11,0,30000,'2023-03-10 12:25:58.084000','Chân gà sốt me siêu ngon',0,_binary '','Chân gà sốt me',0,3,0),(12,0,23000,'2023-03-10 12:26:42.877000','Trà sữa trân châu siêu ngon',0,_binary '','Trà sữa trân châu',0,4,0),(13,0,28000,'2023-03-10 12:27:05.615000','Trà sữa trân châu kem flan siêu ngon',0,_binary '','Trà sữa trân châu kem flan',0,4,0),(14,0,24000,'2023-03-10 12:28:29.034000','Trà đào của Vi siêu lực',0,_binary '','Trà đào của Vi',0,4,0),(15,0,12000,'2023-03-10 12:28:45.752000','Trà đào của Vi siêu lực',0,_binary '','Trà tắc của Heimerdinger',0,4,0),(16,0,34000,'2023-03-10 12:29:12.732000','Trà nho mỹ của Yone siêu ngọt',0,_binary '','Trà nho mỹ của Yone',0,4,0),(17,0,20000,'2023-03-10 12:30:21.744000','Su kem Poppy siêu ngọt',0,_binary '','Su kem Poppy',0,5,0),(18,0,25000,'2023-03-10 12:30:50.532000','Su kem trân châu dâu sữa nguyên bản',0,_binary '','Su kem trân châu dâu sữa',0,5,0),(19,0,15000,'2023-03-10 12:32:33.605000','Bánh tráng trộn size nhỏ',0,_binary '','Bánh tráng trộn (S)',0,6,0),(20,0,20000,'2023-03-10 12:32:45.765000','Bánh tráng trộn size lớn',0,_binary '','Bánh tráng trộn (L)',0,6,0),(21,0,20000,'2023-03-10 12:33:05.094000','Bánh tráng bơ nyc size nhỏ',0,_binary '','Bánh tráng bơ nyc (S)',0,6,0),(22,0,24000,'2023-03-10 12:33:19.086000','Bánh tráng bơ nyc size lớn, thêm bơ',15,_binary '','Bánh tráng bơ nyc (l)',0,6,0),(52,0,15000,'2023-04-03 09:29:31.432000','Bánh mỳ sữa, đặt biệt thơm ngon',0,_binary '','Bánh mỳ sữa',0,1,0),(53,0,25000,'2023-04-06 11:18:50.362000','Bánh kem sữa siêu ngon',0,_binary '','Bánh kem sữa',0,1,0),(54,0,35000,'2023-04-06 11:22:53.468000','Nước ép trái cây lên men nguyên chất',0,_binary '','Nước ép jinro',0,1,0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_users`
--

DROP TABLE IF EXISTS `products_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_users` (
  `product_id` bigint NOT NULL,
  `users_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`,`users_id`),
  KEY `FKd13uw6h7blk7bbsak0o42sq1e` (`users_id`),
  CONSTRAINT `FK3a4vga93rps9qulitebjriw18` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKd13uw6h7blk7bbsak0o42sq1e` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_users`
--

LOCK TABLES `products_users` WRITE;
/*!40000 ALTER TABLE `products_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(4,'ROLE_SHIPPER'),(3,'ROLE_SHOP'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippers`
--

DROP TABLE IF EXISTS `shippers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shippers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `is_shipping` bit(1) DEFAULT NULL,
  `shop_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjrqs5ealixmok3nvwqgs0n21b` (`shop_id`),
  KEY `FK3aekq9ie8kebm7202pyqlsoqs` (`user_id`),
  CONSTRAINT `FK3aekq9ie8kebm7202pyqlsoqs` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjrqs5ealixmok3nvwqgs0n21b` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippers`
--

LOCK TABLES `shippers` WRITE;
/*!40000 ALTER TABLE `shippers` DISABLE KEYS */;
INSERT INTO `shippers` VALUES (1,_binary '',_binary '',1,13),(2,_binary '',_binary '',1,14),(3,_binary '',_binary '',1,15),(4,_binary '',_binary '\0',1,17),(5,_binary '',_binary '\0',1,18),(7,_binary '',_binary '\0',2,16),(8,_binary '',_binary '\0',2,19),(9,_binary '',_binary '\0',2,22),(10,_binary '',_binary '\0',3,20),(11,_binary '',_binary '\0',4,23),(12,_binary '',_binary '\0',4,24),(13,_binary '',_binary '\0',5,33),(14,_binary '',_binary '\0',5,34),(15,_binary '\0',_binary '\0',1,70);
/*!40000 ALTER TABLE `shippers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shops`
--

DROP TABLE IF EXISTS `shops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shops` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(512) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_student` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfrcvw4bjeifsxtwi7udccb03u` (`user_id`),
  CONSTRAINT `FK34po7mmli7wotimo70r6640ap` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shops`
--

LOCK TABLES `shops` WRITE;
/*!40000 ALTER TABLE `shops` DISABLE KEYS */;
INSERT INTO `shops` VALUES (1,'Shop bán đồ ăn vặt Tung Của','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2F1680013405367?alt=media&token=ea28c5d9-2521-4ed8-bb65-34466a66c57e',_binary '',_binary '','Fuxiji',7,16.0363819,108.2125886),(2,'Shop bán kem Tung Của','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Mixue',8,16.0363819,108.2125886),(3,'Shop gà rán Nhật Bản','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Ni hon gà',9,16.0363819,108.2125886),(4,'Shop trà sữa Hàn Quốc','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Kô re gông chà',10,16.0363819,108.2125886),(5,'Su kem handmade bởi J4Team','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '','J4 Cream Cake',11,16.0363819,108.2125886),(6,'Bánh tráng trộn FPT giá cả luxury','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '','Luxpity',12,16.0363819,108.2125886),(16,'Shop này rất vui','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2F1680235001988?alt=media&token=0735a135-fe44-4fd2-9aa7-1192261b7150',_binary '',_binary '','Shop Vui Vãi',66,16.0363819,108.2125886),(17,'Trường đại học chuyên đi lùa gà','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2F1680768392661?alt=media&token=8ee76495-57b8-4dee-8358-1b23d98f7661',_binary '',_binary '','FBT University Da Nang',72,16.0363819,108.2125886),(20,'Shop chuyên đi lùa gà','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2Fdefault-shop-01.png?alt=media&token=2eb5629a-730b-4611-a564-05deb29c9217',_binary '',_binary '\0','Shop FPT',76,16.0653221,108.1949135),(23,'Shop an lành','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2Fdefault-shop-01.png?alt=media&token=2eb5629a-730b-4611-a564-05deb29c9217',_binary '',_binary '\0','Shop an lành',79,16.0363819,108.2125886),(25,'Asian Food','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2F1681135458492?alt=media&token=48fef198-d66a-43f4-a036-c527aa47b69c',_binary '',_binary '\0','Asian Food',82,16.0653221,108.1949135);
/*!40000 ALTER TABLE `shops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slider` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2FSliders%2Ffbt.jpg?alt=media&token=1482c391-570b-469a-b8f3-6599550a81d3'),(20,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2FSliders%2F1681102671548?alt=media&token=9f0ae7d7-bb6b-4720-9e8e-00ac701bef89');
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `product_cost` varchar(255) DEFAULT NULL,
  `shipping_cost` varchar(255) DEFAULT NULL,
  `total` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpiu8sb2aby57a9iiuqe614hut` (`order_id`),
  CONSTRAINT `FKa8ufxrrpq6xmniblly0v1rhu8` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address` (
  `user_id` bigint NOT NULL,
  `address_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`address_id`),
  KEY `FKdaaxogn1ss81gkcsdn05wi6jp` (`address_id`),
  CONSTRAINT `FKdaaxogn1ss81gkcsdn05wi6jp` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKrmincuqpi8m660j1c57xj7twr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (65,31),(62,40),(2,50),(2,56),(2,57),(66,60),(2,61),(72,64),(76,65),(80,65),(82,65),(7,69),(79,69);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_wishlist`
--

DROP TABLE IF EXISTS `user_wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_wishlist` (
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`product_id`),
  KEY `FKme2h6wt7w92ypitpwsol95j73` (`product_id`),
  CONSTRAINT `FK_product_wishlist` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKme2h6wt7w92ypitpwsol95j73` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKxfiwf0ov64o4j979puogxloy` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wishlist`
--

LOCK TABLES `user_wishlist` WRITE;
/*!40000 ALTER TABLE `user_wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) NOT NULL,
  `dob` varchar(255) NOT NULL,
  `default_address` bigint DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `identified_code` varchar(255) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role_id` bigint DEFAULT NULL,
  `fcm_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKan5x1m1dlph8up8vt08st299q` (`identified_code`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'2023-03-10 11:36:50.889000','1998-11-26',61,'tranvanbanh@gmail.com','Trần Văn Bảnh','201885162','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680098965421?alt=media&token=32121468-c71d-4c24-bae6-71047ca84cbb',_binary '\0','0901000002',2,'123456'),(3,'2023-03-10 11:38:01.863000','1998-11-26',1,'user002@gmail.com','Tran Van User 2','000000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000002',2,'123456'),(4,'2023-03-10 11:38:17.408000','1998-11-26',1,'user003@gmail.com','Tran Van User 3','000000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000003',2,'123456'),(7,'2023-03-10 11:41:31.611000','1998-11-26',1,'khuecuxi3@gmail.com','Khuê Nguyễn','100000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680013404285?alt=media&token=5f8e9cb9-ecdb-4dea-be27-a7b1f34388ee',_binary '\0','0902000001',3,'123456'),(8,'2023-03-10 11:41:45.258000','1995-03-09',1,'shop002@gmail.com','Dang Thi Shop 2','100000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000002',3,'123456'),(9,'2023-03-10 11:41:58.053000','1995-03-09',1,'shop003@gmail.com','Dang Thi Shop 3','100000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000003',3,'123456'),(10,'2023-03-10 11:42:11.335000','1995-03-09',1,'shop004@gmail.com','Dang Thi Shop 4','100000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000004',3,'123456'),(11,'2023-03-10 11:42:41.030000','1995-03-09',1,'shop005@gmail.com','Dang Thi Shop 5','100000005','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000005',3,'123456'),(12,'2023-03-10 11:42:52.954000','1995-03-09',1,'shop006@gmail.com','Dang Thi Shop 6','100000006','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000006',3,'123456'),(13,'2023-03-10 11:48:46.941000','1995-03-09',1,'shipper001@gmail.com','Mai Anh Ship 001','200000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000001',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(14,'2023-03-10 11:49:02.768000','2000-07-15',1,'shipper002@gmail.com','Mai Anh Ship 002','200000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000002',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(15,'2023-03-10 11:49:13.886000','2000-07-15',1,'shipper003@gmail.com','Mai Anh Ship 003','200000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000003',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(16,'2023-03-10 11:49:26.875000','2000-07-15',1,'shipper004@gmail.com','Mai Anh Ship 004','200000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000004',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(17,'2023-03-10 11:49:57.335000','2000-07-15',1,'shipper005@gmail.com','Mai Anh Ship 005','200000005','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000005',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(18,'2023-03-10 11:50:09.179000','2000-07-15',1,'shipper006@gmail.com','Mai Anh Ship 006','200000006','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000006',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(19,'2023-03-10 11:50:23.282000','2000-07-15',1,'shipper007@gmail.com','Mai Anh Ship 007','200000007','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000007',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(20,'2023-03-10 11:50:41.005000','2002-06-22',1,'shipper008@gmail.com','Mai Anh Ship 008','200000008','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000008',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(22,'2023-03-10 11:51:21.520000','2002-06-22',1,'shipper010@gmail.com','Mai Anh Ship 010','200000010','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000010',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(23,'2023-03-10 11:51:37.174000','2002-06-22',1,'shipper011@gmail.com','Mai Anh Ship 011','200000011','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000011',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(24,'2023-03-10 11:51:51.903000','2002-06-22',1,'shipper012@gmail.com','Mai Anh Ship 012','200000012','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000012',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(33,'2023-03-24 14:24:23.884000','1999-12-26',0,'tobecontinued001@gmail.com','Nguyễn Văn Per','2016651752','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1679642661469?alt=media&token=83b544cf-9848-4e7f-b3d9-7921aad08d51',_binary '\0','0905000988',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(34,'2023-03-25 15:56:45.867000','2023-03-09',0,'daxua001@gmail.com','Nguyễn Văn Per','daxua001@gmail.com','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1679734604676?alt=media&token=62d00681-b016-46da-9f6c-e9502cdeab3b',_binary '\0','0917806477',4,'cIVsGl3XSR-Rx0zPBKRgUG:APA91bHpqBURbiS_IHtBjK2ZLYVfJvPX8OIwU7uw-X6MJEhRZUl-1X26mw94byb1ZIxjfd1OCNvBwfGXecKGCAvS-7ou8RnT3O3JMhANwBf-gcEbMhaNzeRoCUzKTJOpsyuh6QzYPOx0'),(62,'2023-03-29 12:32:56.332000','1998-11-26',40,'hamterx@gmail.com','Chuột HamsterX','201662121','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680067975503?alt=media&token=a9ae2349-ad9c-4f3e-b520-adab750d6c7a',_binary '\0','0911667899',2,'123456'),(65,'2023-03-30 17:03:33.642000','1998-11-26',31,'nguyenhakhue1998@gmail.com','Nguyễn Hà Khuê','201773175','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680170612900?alt=media&token=289112a7-a4be-413b-99e8-021e394ec40c',_binary '\0','0917806496',2,'123456'),(66,'2023-03-31 10:56:41.969000','1998-11-26',60,'aishop@gmail.com','Ai Shop','201885188','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680235000759?alt=media&token=74540a70-c61d-470c-8e63-f7e8f9aa0496',_binary '\0','0973124222',3,'123456'),(70,'2023-04-03 09:57:29.190000','2004-02-07',0,'loimaydapvip@gmail.com','Lợi máy khâu','207225568','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680490647584?alt=media&token=dfd3c801-5c57-4003-8d6d-3e72ddebea85',_binary '\0','0935752999',4,'123456'),(72,'2023-04-06 15:06:32.652000','1998-11-26',64,'truonggiabinh@gmail.com','Trương Gia Bình','200637521','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1680768390858?alt=media&token=3670fc81-aa9c-4117-a850-0ae5e8be7d70',_binary '\0','0905111234',3,'123456'),(76,'2023-04-06 17:28:04.143000','1999-11-26',65,'truonggiabinh123@gmail.com','Trương Gia Bình','200695144','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2Fdefault-ava-01.png?alt=media&token=90202711-4b1a-4665-a941-a65bf6c2002e',_binary '\0','0905321455',3,'123456'),(79,'2023-04-10 15:08:49.132000','2000-12-15',69,'pc1515fptk15@gmail.com','Trương Khổng Bình','201985123','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2Fdefault-ava-01.png?alt=media&token=90202711-4b1a-4665-a941-a65bf6c2002e',_binary '\0','0905712312',3,NULL),(80,'2023-04-10 15:57:36.765000','1998-11-26',65,'pyramide.developer@gmail.com','Pyramide Team','201773174','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/UserImages%2F1681117054087?alt=media&token=ad3b7e97-bbab-419e-824d-e4bc336dcf35',_binary '\0','0905762888',1,NULL),(82,'2023-04-10 21:04:18.480000','1998-11-26',65,'khuenhde150062@fpt.edu.vn','Nguyen Van Binh','201997612','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Admin%2Fdefault-ava-01.png?alt=media&token=90202711-4b1a-4665-a941-a65bf6c2002e',_binary '\0','0905123456',3,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wards`
--

DROP TABLE IF EXISTS `wards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wards` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `district_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfjqt744bo800mb5uax74lav8k` (`district_id`),
  CONSTRAINT `FKfjqt744bo800mb5uax74lav8k` FOREIGN KEY (`district_id`) REFERENCES `districts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wards`
--

LOCK TABLES `wards` WRITE;
/*!40000 ALTER TABLE `wards` DISABLE KEYS */;
INSERT INTO `wards` VALUES (1,'Xã Hoà Bắc',1),(2,'Xã Hòa Liên',1),(3,'Xã Hòa Ninh',1),(4,'Xã Hòa Sơn',1),(5,'Xã Hòa Nhơn',1),(6,'Xã Hòa Phú',1),(7,'Xã Hòa Phong',1),(8,'Xã Hòa Châu',1),(9,'Xã Hòa Tiến',1),(10,'Xã Hòa Phước',1),(11,'Xã Hòa Khương',1),(12,'Phường Khuê Trung',3),(13,'Phường Hòa Phát',3),(14,'Phường Hòa An',3),(15,'Phường Hòa Thọ Tây',3),(16,'Phường Hòa Thọ Đông',3),(17,'Phường Hòa Xuân',3),(18,'Phường Thanh Bình',4),(19,'Phường Thuận Phước',4),(20,'Phường Thạch Thang',4),(21,'Phường Hải Châu I',4),(22,'Phường Hải Châu II',4),(23,'Phường Phước Ninh',4),(24,'Phường Hòa Thuận Tây',4),(25,'Phường Hòa Thuận Đông',4),(26,'Phường Nam Dương',4),(27,'Phường Bình Hiên',4),(28,'Phường Bình Thuận',4),(29,'Phường Hòa Cường Bắc',4),(30,'Phường Hòa Cường Nam',4),(31,'Phường Hòa Hiệp Bắc',5),(32,'Phường Hòa Hiệp Nam',5),(33,'Phường Hòa Khánh Bắc',5),(34,'Phường Hòa Khánh Nam',5),(35,'Phường Hòa Minh',5),(36,'Phường Mỹ An',6),(37,'Phường Khuê Mỹ',6),(38,'Phường Hoà Quý',6),(39,'Phường Hoà Hải',6),(40,'Phường Thọ Quang',7),(41,'Phường Nại Hiên Đông',7),(42,'Phường Mân Thái',7),(43,'Phường An Hải Bắc',7),(44,'Phường Phước Mỹ',7),(45,'Phường An Hải Tây',7),(46,'Phường An Hải Đông',7),(47,'Phường Tam Thuận',8),(48,'Phường Thanh Khê Tây',8),(49,'Phường Thanh Khê Đông',8),(50,'Phường Xuân Hà',8),(51,'Phường Tân Chính',8),(52,'Phường Chính Gián',8),(53,'Phường Vĩnh Trung',8),(54,'Phường Thạc Gián',8),(55,'Phường An Khê',8),(56,'Phường Hòa Khê',8);
/*!40000 ALTER TABLE `wards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-11 11:05:23
