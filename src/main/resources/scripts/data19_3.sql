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
  `ward` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKh8jov0p3cffixl6yhdy4hb2gi` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'190 Dien Bien Phu','Thanh Khe','Thanh Khe Dong');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Bánh kem'),(2,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Bánh tráng'),(3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Đồ ăn nhanh'),(4,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Gà'),(5,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Bò'),(6,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Đồ nướng'),(7,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Trà sữa'),(8,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Kem'),(9,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Đồ sấy khô'),(10,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb','Trà');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES (1,'Thanh Khê');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
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
  `address_id` bigint DEFAULT NULL,
  `shipper_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKokwrsbrir8wa5luxqkrjqr53x` (`order_tracking_number`),
  KEY `FKf5464gxwc32ongdvka2rtvw96` (`address_id`),
  KEY `FKsk2tyu7xrdu2ienuay5yrpgoe` (`shipper_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKf5464gxwc32ongdvka2rtvw96` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKsk2tyu7xrdu2ienuay5yrpgoe` FOREIGN KEY (`shipper_id`) REFERENCES `shippers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
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
INSERT INTO `product_categories` VALUES (17,1),(18,1),(19,2),(20,2),(21,2),(22,2),(1,3),(2,3),(3,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(3,4),(9,4),(10,4),(11,4),(1,5),(2,5),(2,6),(12,7),(13,7),(5,8),(6,8),(7,8),(8,8),(17,8),(18,8),(4,9),(14,10),(15,10),(16,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fthitho.png?alt=media&token=ff7f2aae-eea2-4127-b734-3fca9863adbe',1),(2,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fgakho.jpg?alt=media&token=e2b05a78-5a83-44f3-8a64-566bf0bf2650',2),(3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fbokho.jpg?alt=media&token=ce68ab6d-6fc5-47e2-9586-57fdfa1a3f55',2),(4,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fgakho.jpg?alt=media&token=e2b05a78-5a83-44f3-8a64-566bf0bf2650',3),(5,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',4),(6,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',5),(7,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',6),(8,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',7),(9,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',8),(10,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',8),(11,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',9),(12,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',10),(13,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',11),(14,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',12),(15,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',13),(16,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',14),(17,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',15),(18,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fdorama.png?alt=media&token=a4c98775-179e-48f9-a943-2f72e304adf4',16),(19,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',17),(20,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',18),(21,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',19),(22,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',20),(23,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',21),(24,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fnoicom.jpg?alt=media&token=a14cded3-832b-4078-ac9d-e705e8087b14',22),(25,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Food%2Fbokho.jpg?alt=media&token=ce68ab6d-6fc5-47e2-9586-57fdfa1a3f55',2);
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
  PRIMARY KEY (`id`),
  KEY `FK7kp8sbhxboponhx3lxqtmkcoj` (`shop_id`),
  CONSTRAINT `FK7kp8sbhxboponhx3lxqtmkcoj` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,0,45000,'2023-03-10 12:18:55.668000','Thịt hổ ngon bá cháy luôn',10,_binary '','Thịt hổ ngon bá cháy',0,1),(2,0,95000,'2023-03-10 12:19:28.227000','Thịt bò khô ngũ vị mô tả',0,_binary '','Thịt bò khô ngũ vị',0,1),(3,0,95000,'2023-03-10 12:20:30.041000','Thịt gà sẫy AK-47 ngon',0,_binary '','Thịt gà sấy AK-47',0,1),(4,0,30000,'2023-03-10 12:22:02.836000','Rau củ sấy bằng Ulti Lucian, Senna vô sấy chung',0,_binary '','Rau củ sấy bằng Ulti Lucian',0,1),(5,0,10000,'2023-03-10 12:23:14.287000','Kem cây siêu rẻ',0,_binary '','Kem cây',0,2),(6,0,15000,'2023-03-10 12:23:28.066000','Kem cây siêu rẻ',0,_binary '','Kem cây socola',0,2),(7,0,25000,'2023-03-10 12:23:54.059000','Super Yasuo Ultimate',0,_binary '','Super Yasuo',0,2),(8,0,30000,'2023-03-10 12:24:13.883000','Super Yasuo Ultimate Sundae siêu ngon',0,_binary '','Super Yasuo Sundae',0,2),(9,0,27500,'2023-03-10 12:25:07.699000','Đùi gà rán chiên giòn',0,_binary '','Đùi gà rán',0,3),(10,0,27500,'2023-03-10 12:25:18.980000','Đùi gà rán chiên giòn siêu cay',0,_binary '','Đùi gà rán siêu cay',0,3),(11,0,30000,'2023-03-10 12:25:58.084000','Chân gà sốt me siêu ngon',0,_binary '','Chân gà sốt me',0,3),(12,0,23000,'2023-03-10 12:26:42.877000','Trà sữa trân châu siêu ngon',0,_binary '','Trà sữa trân châu',0,4),(13,0,28000,'2023-03-10 12:27:05.615000','Trà sữa trân châu kem flan siêu ngon',0,_binary '','Trà sữa trân châu kem flan',0,4),(14,0,24000,'2023-03-10 12:28:29.034000','Trà đào của Vi siêu lực',0,_binary '','Trà đào của Vi',0,4),(15,0,12000,'2023-03-10 12:28:45.752000','Trà đào của Vi siêu lực',0,_binary '','Trà tắc của Heimerdinger',0,4),(16,0,34000,'2023-03-10 12:29:12.732000','Trà nho mỹ của Yone siêu ngọt',0,_binary '','Trà nho mỹ của Yone',0,4),(17,0,20000,'2023-03-10 12:30:21.744000','Su kem Poppy siêu ngọt',0,_binary '','Su kem Poppy',0,5),(18,0,25000,'2023-03-10 12:30:50.532000','Su kem trân châu dâu sữa nguyên bản',0,_binary '','Su kem trân châu dâu sữa',0,5),(19,0,15000,'2023-03-10 12:32:33.605000','Bánh tráng trộn size nhỏ',0,_binary '','Bánh tráng trộn (S)',0,6),(20,0,20000,'2023-03-10 12:32:45.765000','Bánh tráng trộn size lớn',0,_binary '','Bánh tráng trộn (L)',0,6),(21,0,20000,'2023-03-10 12:33:05.094000','Bánh tráng bơ nyc size nhỏ',0,_binary '','Bánh tráng bơ nyc (S)',0,6),(22,0,24000,'2023-03-10 12:33:19.086000','Bánh tráng bơ nyc size lớn, thêm bơ',0,_binary '','Bánh tráng bơ nyc (L)',0,6);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippers`
--

LOCK TABLES `shippers` WRITE;
/*!40000 ALTER TABLE `shippers` DISABLE KEYS */;
INSERT INTO `shippers` VALUES (1,NULL,_binary '\0',1,13),(2,_binary '\0',_binary '\0',1,14),(3,_binary '\0',_binary '\0',1,15),(4,_binary '\0',_binary '\0',1,17),(5,_binary '\0',_binary '\0',1,18),(6,_binary '\0',_binary '\0',1,21),(7,_binary '\0',_binary '\0',2,16),(8,_binary '\0',_binary '\0',2,19),(9,_binary '\0',_binary '\0',2,22),(10,_binary '\0',_binary '\0',3,20),(11,_binary '\0',_binary '\0',4,23),(12,_binary '\0',_binary '\0',4,24);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfrcvw4bjeifsxtwi7udccb03u` (`user_id`),
  CONSTRAINT `FK34po7mmli7wotimo70r6640ap` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shops`
--

LOCK TABLES `shops` WRITE;
/*!40000 ALTER TABLE `shops` DISABLE KEYS */;
INSERT INTO `shops` VALUES (1,'Shop bán đồ ăn vặt Tung Của','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Fuxiji',7),(2,'Shop bán kem Tung Của','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Mixue',8),(3,'Shop gà rán Nhật Bản','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Ni hon gà',9),(4,'Shop trà sữa Hàn Quốc','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '\0','Kô re gông chà',10),(5,'Su kem handmade bởi J4Team','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '','J4 Cream Cake',11),(6,'Bánh tráng trộn FPT giá cả luxury','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Shops%2Fshopee.jpg?alt=media&token=738c4968-55e4-492a-ac9f-6e172683e72f',_binary '',_binary '','Luxpity',12);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(2,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(4,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(5,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5');
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
  CONSTRAINT `FKme2h6wt7w92ypitpwsol95j73` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKxfiwf0ov64o4j979puogxloy` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wishlist`
--

LOCK TABLES `user_wishlist` WRITE;
/*!40000 ALTER TABLE `user_wishlist` DISABLE KEYS */;
INSERT INTO `user_wishlist` VALUES (1,1);
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
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `identified_code` varchar(255) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role_id` bigint DEFAULT NULL,
  `default_address` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKan5x1m1dlph8up8vt08st299q` (`identified_code`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2023-03-10 11:31:00.165000','26-11-1998','admin@gmail.com','Nguyen Ha Khue','261119980','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0905000001',1,1),(2,'2023-03-10 11:36:50.889000','01-01-2000','user001@gmail.com','Tran Van User 1','000000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000001',2,1),(3,'2023-03-10 11:38:01.863000','01-01-2000','user002@gmail.com','Tran Van User 2','000000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000002',2,1),(4,'2023-03-10 11:38:17.408000','01-01-2000','user003@gmail.com','Tran Van User 3','000000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000003',2,1),(5,'2023-03-10 11:38:31.214000','01-01-2000','user004@gmail.com','Tran Van User 4','000000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000004',2,1),(7,'2023-03-10 11:41:31.611000','01-01-1999','shop001@gmail.com','Dang Thi Shop 1','100000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000001',3,1),(8,'2023-03-10 11:41:45.258000','01-01-1999','shop002@gmail.com','Dang Thi Shop 2','100000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000002',3,1),(9,'2023-03-10 11:41:58.053000','01-01-1999','shop003@gmail.com','Dang Thi Shop 3','100000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000003',3,1),(10,'2023-03-10 11:42:11.335000','01-01-1999','shop004@gmail.com','Dang Thi Shop 4','100000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000004',3,1),(11,'2023-03-10 11:42:41.030000','05-08-2001','shop005@gmail.com','Dang Thi Shop 5','100000005','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000005',3,1),(12,'2023-03-10 11:42:52.954000','05-08-2001','shop006@gmail.com','Dang Thi Shop 6','100000006','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000006',3,1),(13,'2023-03-10 11:48:46.941000','08-6-1997','shipper001@gmail.com','Mai Anh Ship 001','200000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000001',4,1),(14,'2023-03-10 11:49:02.768000','08-6-1997','shipper002@gmail.com','Mai Anh Ship 002','200000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000002',4,1),(15,'2023-03-10 11:49:13.886000','08-6-1997','shipper003@gmail.com','Mai Anh Ship 003','200000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000003',4,1),(16,'2023-03-10 11:49:26.875000','08-6-1997','shipper004@gmail.com','Mai Anh Ship 004','200000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000004',4,1),(17,'2023-03-10 11:49:57.335000','12-10-2002','shipper005@gmail.com','Mai Anh Ship 005','200000005','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000005',4,1),(18,'2023-03-10 11:50:09.179000','12-10-2002','shipper006@gmail.com','Mai Anh Ship 006','200000006','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000006',4,1),(19,'2023-03-10 11:50:23.282000','12-10-2002','shipper007@gmail.com','Mai Anh Ship 007','200000007','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000007',4,1),(20,'2023-03-10 11:50:41.005000','12-10-2002','shipper008@gmail.com','Mai Anh Ship 008','200000008','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000008',4,1),(21,'2023-03-10 11:51:05.111000','29-7-2004','shipper009@gmail.com','Mai Anh Ship 009','200000009','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000009',4,1),(22,'2023-03-10 11:51:21.520000','29-7-2004','shipper010@gmail.com','Mai Anh Ship 010','200000010','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000010',4,1),(23,'2023-03-10 11:51:37.174000','29-7-2004','shipper011@gmail.com','Mai Anh Ship 011','200000011','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000011',4,1),(24,'2023-03-10 11:51:51.903000','29-7-2004','shipper012@gmail.com','Mai Anh Ship 012','200000012','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000012',4,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wards`
--

LOCK TABLES `wards` WRITE;
/*!40000 ALTER TABLE `wards` DISABLE KEYS */;
INSERT INTO `wards` VALUES (1,'Thanh Khe Tay',1);
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

-- Dump completed on 2023-03-19 15:54:44
