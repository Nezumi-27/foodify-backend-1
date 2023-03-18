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
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'assets/categories/1.png','Bánh kem'),(2,'assets/categories/2.png','Bánh tráng'),(3,'assets/categories/3.png','Đồ ăn nhanh'),(4,'assets/categories/4.png','Gà'),(5,'assets/categories/5.png','Bò'),(6,'assets/categories/6.png','Đồ nướng'),(7,'assets/categories/7.png','Trà sữa'),(8,'assets/categories/8.png','Kem'),(9,'assets/categories/9.png','Đồ sấy khô'),(10,'assets/categories/10.png','Trà');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES (1,'Thanh Khê');
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
INSERT INTO `product_categories` VALUES (17,1),(18,1),(19,2),(20,2),(21,2),(22,2),(1,3),(2,3),(3,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(17,3),(18,3),(19,3),(20,3),(21,3),(22,3),(3,4),(9,4),(10,4),(11,4),(1,5),(2,5),(2,6),(12,7),(13,7),(5,8),(6,8),(7,8),(8,8),(17,8),(18,8),(4,9),(14,10),(15,10),(16,10);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,0,45000,'2023-03-10 12:18:55.668000','Thịt hổ ngon bá cháy luôn',0,_binary '','Thịt hổ ngon bá cháy',0,1),(2,0,95000,'2023-03-10 12:19:28.227000','Thịt bò khô ngũ vị mô tả',0,_binary '','Thịt bò khô ngũ vị',0,1),(3,0,95000,'2023-03-10 12:20:30.041000','Thịt gà sẫy AK-47 ngon',0,_binary '','Thịt gà sấy AK-47',0,1),(4,0,30000,'2023-03-10 12:22:02.836000','Rau củ sấy bằng Ulti Lucian, Senna vô sấy chung',0,_binary '','Rau củ sấy bằng Ulti Lucian',0,1),(5,0,10000,'2023-03-10 12:23:14.287000','Kem cây siêu rẻ',0,_binary '','Kem cây',0,2),(6,0,15000,'2023-03-10 12:23:28.066000','Kem cây siêu rẻ',0,_binary '','Kem cây socola',0,2),(7,0,25000,'2023-03-10 12:23:54.059000','Super Yasuo Ultimate',0,_binary '','Super Yasuo',0,2),(8,0,30000,'2023-03-10 12:24:13.883000','Super Yasuo Ultimate Sundae siêu ngon',0,_binary '','Super Yasuo Sundae',0,2),(9,0,27500,'2023-03-10 12:25:07.699000','Đùi gà rán chiên giòn',0,_binary '','Đùi gà rán',0,3),(10,0,27500,'2023-03-10 12:25:18.980000','Đùi gà rán chiên giòn siêu cay',0,_binary '','Đùi gà rán siêu cay',0,3),(11,0,30000,'2023-03-10 12:25:58.084000','Chân gà sốt me siêu ngon',0,_binary '','Chân gà sốt me',0,3),(12,0,23000,'2023-03-10 12:26:42.877000','Trà sữa trân châu siêu ngon',0,_binary '','Trà sữa trân châu',0,4),(13,0,28000,'2023-03-10 12:27:05.615000','Trà sữa trân châu kem flan siêu ngon',0,_binary '','Trà sữa trân châu kem flan',0,4),(14,0,24000,'2023-03-10 12:28:29.034000','Trà đào của Vi siêu lực',0,_binary '','Trà đào của Vi',0,4),(15,0,12000,'2023-03-10 12:28:45.752000','Trà đào của Vi siêu lực',0,_binary '','Trà tắc của Heimerdinger',0,4),(16,0,34000,'2023-03-10 12:29:12.732000','Trà nho mỹ của Yone siêu ngọt',0,_binary '','Trà nho mỹ của Yone',0,4),(17,0,20000,'2023-03-10 12:30:21.744000','Su kem Poppy siêu ngọt',0,_binary '','Su kem Poppy',0,5),(18,0,25000,'2023-03-10 12:30:50.532000','Su kem trân châu dâu sữa nguyên bản',0,_binary '','Su kem trân châu dâu sữa',0,5),(19,0,15000,'2023-03-10 12:32:33.605000','Bánh tráng trộn size nhỏ',0,_binary '','Bánh tráng trộn (S)',0,6),(20,0,20000,'2023-03-10 12:32:45.765000','Bánh tráng trộn size lớn',0,_binary '','Bánh tráng trộn (L)',0,6),(21,0,20000,'2023-03-10 12:33:05.094000','Bánh tráng bơ nyc size nhỏ',0,_binary '','Bánh tráng bơ nyc (S)',0,6),(22,0,24000,'2023-03-10 12:33:19.086000','Bánh tráng bơ nyc size lớn, thêm bơ',0,_binary '','Bánh tráng bơ nyc (L)',0,6);
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
INSERT INTO `shippers` VALUES (1,NULL,_binary '\0',1,13),(2,_binary '\0',_binary '\0',1,14),(3,_binary '\0',_binary '\0',1,15),(4,_binary '\0',_binary '\0',1,17),(5,_binary '\0',_binary '\0',1,18),(6,_binary '\0',_binary '\0',1,21),(7,_binary '\0',_binary '\0',2,16),(8,_binary '\0',_binary '\0',2,19),(9,_binary '\0',_binary '\0',2,22),(10,_binary '\0',_binary '\0',3,20),(11,_binary '\0',_binary '\0',4,23),(12,_binary '\0',_binary '\0',4,24);
/*!40000 ALTER TABLE `shippers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shops`
--

LOCK TABLES `shops` WRITE;
/*!40000 ALTER TABLE `shops` DISABLE KEYS */;
INSERT INTO `shops` VALUES (1,'Shop bán đồ ăn vặt Tung Của','assets/shops/1.jpg',_binary '',_binary '\0','Fuxiji',7),(2,'Shop bán kem Tung Của','assets/shops/2.jpg',_binary '',_binary '\0','Mixue',8),(3,'Shop gà rán Nhật Bản','assets/shops/3.jpg',_binary '',_binary '\0','Ni hon gà',9),(4,'Shop trà sữa Hàn Quốc','assets/shops/4.jpg',_binary '',_binary '\0','Kô re gông chà',10),(5,'Su kem handmade bởi J4Team','assets/shops/5.jpg',_binary '',_binary '','J4 Cream Cake',11),(6,'Bánh tráng trộn FPT giá cả luxury','assets/shops/6.jpg',_binary '',_binary '','Luxpity',12);
/*!40000 ALTER TABLE `shops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(2,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(3,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(4,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5'),(5,'https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/Free%20Food%20Advertising%20Banner%20Template.jpg?alt=media&token=8b836fd7-f18f-46c6-a14f-27680e51a1d5');
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_wishlist`
--

LOCK TABLES `user_wishlist` WRITE;
/*!40000 ALTER TABLE `user_wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2023-03-10 11:31:00.165000','26-11-1998','admin@gmail.com','Nguyen Ha Khue','261119980','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0905000001',1),(2,'2023-03-10 11:36:50.889000','01-01-2000','user001@gmail.com','Tran Van User 1','000000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000001',2),(3,'2023-03-10 11:38:01.863000','01-01-2000','user002@gmail.com','Tran Van User 2','000000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000002',2),(4,'2023-03-10 11:38:17.408000','01-01-2000','user003@gmail.com','Tran Van User 3','000000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000003',2),(5,'2023-03-10 11:38:31.214000','01-01-2000','user004@gmail.com','Tran Van User 4','000000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0901000004',2),(7,'2023-03-10 11:41:31.611000','01-01-1999','shop001@gmail.com','Dang Thi Shop 1','100000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000001',3),(8,'2023-03-10 11:41:45.258000','01-01-1999','shop002@gmail.com','Dang Thi Shop 2','100000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000002',3),(9,'2023-03-10 11:41:58.053000','01-01-1999','shop003@gmail.com','Dang Thi Shop 3','100000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000003',3),(10,'2023-03-10 11:42:11.335000','01-01-1999','shop004@gmail.com','Dang Thi Shop 4','100000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000004',3),(11,'2023-03-10 11:42:41.030000','05-08-2001','shop005@gmail.com','Dang Thi Shop 5','100000005','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000005',3),(12,'2023-03-10 11:42:52.954000','05-08-2001','shop006@gmail.com','Dang Thi Shop 6','100000006','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0902000006',3),(13,'2023-03-10 11:48:46.941000','08-6-1997','shipper001@gmail.com','Mai Anh Ship 001','200000001','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000001',4),(14,'2023-03-10 11:49:02.768000','08-6-1997','shipper002@gmail.com','Mai Anh Ship 002','200000002','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000002',4),(15,'2023-03-10 11:49:13.886000','08-6-1997','shipper003@gmail.com','Mai Anh Ship 003','200000003','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000003',4),(16,'2023-03-10 11:49:26.875000','08-6-1997','shipper004@gmail.com','Mai Anh Ship 004','200000004','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000004',4),(17,'2023-03-10 11:49:57.335000','12-10-2002','shipper005@gmail.com','Mai Anh Ship 005','200000005','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000005',4),(18,'2023-03-10 11:50:09.179000','12-10-2002','shipper006@gmail.com','Mai Anh Ship 006','200000006','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000006',4),(19,'2023-03-10 11:50:23.282000','12-10-2002','shipper007@gmail.com','Mai Anh Ship 007','200000007','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000007',4),(20,'2023-03-10 11:50:41.005000','12-10-2002','shipper008@gmail.com','Mai Anh Ship 008','200000008','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000008',4),(21,'2023-03-10 11:51:05.111000','29-7-2004','shipper009@gmail.com','Mai Anh Ship 009','200000009','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000009',4),(22,'2023-03-10 11:51:21.520000','29-7-2004','shipper010@gmail.com','Mai Anh Ship 010','200000010','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000010',4),(23,'2023-03-10 11:51:37.174000','29-7-2004','shipper011@gmail.com','Mai Anh Ship 011','200000011','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000011',4),(24,'2023-03-10 11:51:51.903000','29-7-2004','shipper012@gmail.com','Mai Anh Ship 012','200000012','https://firebasestorage.googleapis.com/v0/b/foodify-55954.appspot.com/o/330279460_921995832174858_116554411020824696_n.jpg?alt=media&token=2e79076a-75f6-4175-9b01-3e22bf3134cb',_binary '\0','0903000012',4),(25,'2023-03-14 22:37:04.831000','27-12-2000','khue@gmail.com','Nguyen Ha Khue','123123222','assets/users/1.png',_binary '','0902000099',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2023-03-14 22:58:42
