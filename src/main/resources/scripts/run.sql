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
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'05 Dien Bien Phu','Thanh Khe','Thanh Khe Dong'),(2,'536 Dien Bien Phu','Thanh Khe','Thanh Khe Dong'),(3,'Hoang Hoa Tham','Thanh Khe','An Khe');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'assets/categories/1.png','Category 001'),(2,'assets/categories/2.png','Category 002'),(3,'assets/categories/3.png','Category 003'),(4,'assets/categories/4.png','Category 004'),(5,'assets/categories/5.png','Category 005');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,1),(2,1),(6,1),(7,1),(1,2),(3,2),(4,2),(2,3),(3,3),(4,3),(5,3),(7,3),(3,4),(4,4),(5,5),(6,5);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
INSERT INTO `product_images` VALUES (1,'assets/products/images/1.png',1),(2,'assets/products/images/2.png',1),(3,'assets/products/images/3.png',1),(4,'assets/products/images/4.png',2),(5,'assets/products/images/5.png',2),(6,'assets/products/images/6.png',2),(7,'assets/products/images/7.png',3),(8,'assets/products/images/8.png',3),(9,'assets/products/images/9.png',4);
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,0,15000,'2023-03-02 17:09:41.117000','Product 001 Description',0,_binary '','Product 001',0,1),(2,0,15000,'2023-03-02 17:09:53.003000','Product 002 Description',0,_binary '','Product 002',0,1),(3,0,15000,'2023-03-02 17:10:14.417000','Product 003 Description',0,_binary '','Product 003',0,1),(4,0,18000,'2023-03-02 17:10:25.286000','Product 004 Description',0,_binary '','Product 004',0,1),(5,0,20000,'2023-03-02 17:10:41.461000','Product 005 Description',0,_binary '','Product 005',0,1),(6,0,20000,'2023-03-02 17:10:53.259000','Product 006 Description',0,_binary '','Product 006',0,2),(7,0,17000,'2023-03-02 17:11:04.996000','Product 007 Description',0,_binary '','Product 007',0,2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(4,'ROLE_SHIPPER'),(3,'ROLE_SHOP'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shippers`
--

LOCK TABLES `shippers` WRITE;
/*!40000 ALTER TABLE `shippers` DISABLE KEYS */;
INSERT INTO `shippers` VALUES (1,_binary '',1,4),(2,_binary '\0',1,5),(3,_binary '\0',1,6),(4,_binary '\0',1,7),(5,_binary '\0',1,8),(6,_binary '\0',2,9),(7,_binary '\0',2,10),(8,_binary '\0',2,11);
/*!40000 ALTER TABLE `shippers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shops`
--

LOCK TABLES `shops` WRITE;
/*!40000 ALTER TABLE `shops` DISABLE KEYS */;
INSERT INTO `shops` VALUES (1,'Shop 001','assets/shop/001.png',_binary '',_binary '',12),(2,'Shop 002','assets/shop/002.png',_binary '',_binary '',13),(3,'Shop 003','assets/shop/003.png',_binary '',_binary '\0',14),(4,'Shop 004','assets/shop/004.png',_binary '',_binary '',15);
/*!40000 ALTER TABLE `shops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (1,1),(2,2),(1,3);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_wishlist`
--

LOCK TABLES `user_wishlist` WRITE;
/*!40000 ALTER TABLE `user_wishlist` DISABLE KEYS */;
INSERT INTO `user_wishlist` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2023-03-01 15:41:13.922000','25-11-2023','user2023@gmail.com','Nguyen User','121212121','assets/users/2023.png',_binary '\0','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','0123456789',1),(2,'2023-03-01 15:42:00.701000','25-11-1998','user002@gmail.com','Nguyen User 002','000000002','assets/users/u002.png',_binary '\0','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','000000002',2),(3,'2023-03-01 15:43:34.296000','25-11-1998','user003@gmail.com','Nguyen User 003','000000003','assets/users/u003.png',_binary '\0','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','000000003',2),(4,'2023-03-01 15:45:06.212000','25-11-1998','shipper001@gmail.com','Tran Shipper 001','sp000000001','assets/users/sp001.png',_binary '\0','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp000000001',4),(5,'2023-03-01 15:45:18.660000','25-11-1998','shipper002@gmail.com','Tran Shipper 002','sp000000002','assets/users/sp002.png',_binary '\0','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp000000002',4),(6,'2023-03-01 15:46:54.314000','27-12-2000','shipper003@gmail.com','Tran Shipper 003','sp0000003','assets/users/sp03.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp00000003',4),(7,'2023-03-01 15:47:11.738000','27-12-2000','shipper004@gmail.com','Tran Shipper 004','sp0000004','assets/users/sp04.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp00000004',4),(8,'2023-03-01 15:47:27.182000','27-12-2000','shipper005@gmail.com','Tran Shipper 005','sp0000005','assets/users/sp05.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp00000005',4),(9,'2023-03-01 15:47:42.084000','27-12-2000','shipper006@gmail.com','Tran Shipper 006','sp0000006','assets/users/sp06.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp00000006',4),(10,'2023-03-01 15:47:53.025000','27-12-2000','shipper007@gmail.com','Tran Shipper 007','sp0000007','assets/users/sp07.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp00000007',4),(11,'2023-03-01 15:48:03.324000','27-12-2000','shipper008@gmail.com','Tran Shipper 008','sp0000008','assets/users/sp08.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','sp00000008',4),(12,'2023-03-01 15:48:59.477000','27-12-2000','shop001@gmail.com','Dang Shop 001','s0000001','assets/users/s01.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','s00000001',3),(13,'2023-03-01 15:49:15.358000','27-12-2000','shop002@gmail.com','Dang Shop 002','s0000002','assets/users/s02.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','s00000002',3),(14,'2023-03-01 15:49:28.326000','27-12-2000','shop003@gmail.com','Dang Shop 003','s0000003','assets/users/s03.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','s00000003',3),(15,'2023-03-01 15:49:38.894000','27-12-2000','shop004@gmail.com','Dang Shop 004','s0000004','assets/users/s04.png',_binary '','$2a$10$UY30rmqGKH/AijNBAycpDuY9csJZAA/p3DJLXdm8LBCnIcC0GZte.','s00000004',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `wards`
--

LOCK TABLES `wards` WRITE;
/*!40000 ALTER TABLE `wards` DISABLE KEYS */;
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

-- Dump completed on 2023-03-02 17:31:03
