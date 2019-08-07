use syslisten;
drop table if exists monitor;
CREATE TABLE `monitor` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `osName` varchar(20) DEFAULT NULL,
   `memoryPercent` int(11) DEFAULT NULL,
   `totalMemory` bigint(20) DEFAULT NULL,
   `usedMemory` bigint(20) DEFAULT NULL,
   `cpuRatio` double DEFAULT NULL,
   `totalDiscSize` bigint(20) DEFAULT NULL,
   `freeDiscSize` bigint(20) DEFAULT NULL,
   `discUsage` int(11) DEFAULT NULL,
   `rxBytes` bigint(20) DEFAULT NULL,
   `txBytes` bigint(20) DEFAULT NULL,
   `savedTime` timestamp default CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from monitor;