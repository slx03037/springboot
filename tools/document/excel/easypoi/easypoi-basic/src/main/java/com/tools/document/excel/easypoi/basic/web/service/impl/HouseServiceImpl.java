//package com.tools.document.excel.easypoi.basic.web.service.impl;
//
//import cn.hutool.core.lang.UUID;
//import com.tools.document.excel.easypoi.basic.web.model.entity.House;
//import com.tools.document.excel.easypoi.basic.web.service.HouseService;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.PictureData;
//import org.apache.poi.xssf.usermodel.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * @author shenlx
// * @description
// * @date 2026/6/24 14:03
// */
//public class HouseServiceImpl implements HouseService {
//    public int importData(MultipartFile file) {
//        // 判断文件后缀
//        String filename = file.getOriginalFilename();
//        String fileSuffixName = filename.substring(filename.lastIndexOf("."));
//        List<House> insertHouseList = new ArrayList<>();
//        if("xlsx".equals(fileSuffixName)) {
//            XSSFWorkbook xssfWorkbook = null;// 工作簿
//            try {
//                xssfWorkbook = new XSSFWorkbook(file.getInputStream());
//                XSSFSheet sheet = xssfWorkbook.getSheetAt(0);// 工作表
//                int lastRowNum = sheet.getLastRowNum();// 获取最后一行序号,从零开始
//                Map<String, List<PictureData>> picMap = new HashMap<>();// 存储图片信息和坐标
//                List<XSSFShape> list = sheet.getDrawingPatriarch().getShapes();
//                if (list != null && list.size() > 0) {// 处理获取图片信息和坐标
//                    list = list.stream().filter(item -> item instanceof XSSFPicture).collect(Collectors.toList());// 过滤出图片的数据
//                    for (XSSFShape xssfShape : list) {
//                        XSSFPicture xSSFPicture = (XSSFPicture) xssfShape;
//                        XSSFClientAnchor xSSFClientAnchor = (XSSFClientAnchor) xSSFPicture.getAnchor();
//                        PictureData pictureData = xSSFPicture.getPictureData();
//                        String point = String.valueOf(xSSFClientAnchor.getRow1());
//                        // 如果存在这个坐标KEY表示相同单元格中的图片,直接集合添加该图片,不存在该坐标key直接创建添加
//                        if (picMap.containsKey(point)) {
//                            picMap.get(point).add(pictureData);
//                        } else {
//                            List<PictureData> arrayList = new ArrayList<PictureData>();
//                            arrayList.add(pictureData);
//                            picMap.put(point, arrayList);
//                        }
//                    }
//                }
//                List<String> image = new ArrayList<>();
//
//                for (int i = 1; i <= lastRowNum; i++) {
//                    XSSFRow row = sheet.getRow(i);
//                    String postalCode = row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue();
//                    String city = row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue();
//                    String street = row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue();
//                    String houseName = row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue();
//                    XSSFCell roomNumberCell = row.getCell(4);
//                    roomNumberCell.setCellType(CellType.STRING);
//                    String roomNumber = row.getCell(4) == null ? "" : roomNumberCell.getStringCellValue();
//                    double houseRent = row.getCell(5) == null ? 0 : row.getCell(5).getNumericCellValue();
//                    String detailAddress = row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue();
//                    int bedroomCount = row.getCell(7) == null ? 0 : (int)row.getCell(7).getNumericCellValue();
//                    int toiletCount = row.getCell(8) == null ? 0 : (int)row.getCell(8).getNumericCellValue();
//                    String furniture = row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue();
//                    String sellingPoint = row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue();
//                    double serviceAmount = row.getCell(11) == null ? 0 : row.getCell(11).getNumericCellValue();
//                    XSSFCell energyLevelCell = row.getCell(12);
//                    energyLevelCell.setCellType(CellType.STRING);
//                    String energyLevel = row.getCell(12) == null ? "" : energyLevelCell.getStringCellValue();
//                    // 获取图片数据
//                    List<PictureData> pictureDataList = picMap.get(String.valueOf(i));
//                    String paths = "";
//                    setHouse(userId, image, insertHouseList, postalCode, city, street, houseName, roomNumber, houseRent,
//                            detailAddress, bedroomCount, toiletCount, furniture, sellingPoint, serviceAmount, energyLevel,
//                            pictureDataList, paths);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else if ("xls".equals(fileSuffixName)) {
//            HSSFWorkbook hssfWorkbook = null;// 工作簿
//            try {
//                hssfWorkbook = new HSSFWorkbook(file.getInputStream());
//                HSSFSheet sheet = hssfWorkbook.getSheetAt(0);// 工作表
//                int lastRowNum = sheet.getLastRowNum();// 获取最后一行序号,从零开始
//                Map<String, List<PictureData>> picMap = new HashMap<>();// 存储图片信息和坐标
//                List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
//                if (list != null && list.size() > 0) {// 处理获取图片信息和坐标
//                    list = list.stream().filter(item -> item instanceof HSSFPicture).collect(Collectors.toList());// 过滤出图片的数据
//                    for (HSSFShape hssfShape : list) {
//                        HSSFPicture hSSFPicture = (HSSFPicture) hssfShape;
//                        HSSFClientAnchor hSSFClientAnchor = (HSSFClientAnchor) hSSFPicture.getAnchor();
//                        PictureData pictureData = hSSFPicture.getPictureData();
//                        String point = String.valueOf(hSSFClientAnchor.getRow1());
//                        // 如果存在这个坐标KEY表示相同单元格中的图片,直接集合添加该图片,不存在该坐标key直接创建添加
//                        if (picMap.containsKey(point)) {
//                            picMap.get(point).add(pictureData);
//                        } else {
//                            List<PictureData> arrayList = new ArrayList<PictureData>();
//                            arrayList.add(pictureData);
//                            picMap.put(point, arrayList);
//                        }
//                    }
//                }
//                List<String> image = new ArrayList<>();
//                for (int i = 1; i <= lastRowNum; i++) {
//                    HSSFRow row = sheet.getRow(i);
//                    String postalCode = row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue();
//                    String city = row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue();
//                    String street = row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue();
//                    String houseName = row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue();
//                    HSSFCell roomNumberCell = row.getCell(4);
//                    roomNumberCell.setCellType(CellType.STRING);
//                    String roomNumber = row.getCell(4) == null ? "" : roomNumberCell.getStringCellValue();
//                    double houseRent = row.getCell(5) == null ? 0 : row.getCell(5).getNumericCellValue();
//                    String detailAddress = row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue();
//                    int bedroomCount = row.getCell(7) == null ? 0 : (int)row.getCell(7).getNumericCellValue();
//                    int toiletCount = row.getCell(8) == null ? 0 : (int)row.getCell(8).getNumericCellValue();
//                    String furniture = row.getCell(9) == null ? "" : row.getCell(9).getStringCellValue();
//                    String sellingPoint = row.getCell(10) == null ? "" : row.getCell(10).getStringCellValue();
//                    double serviceAmount = row.getCell(11) == null ? 0 : row.getCell(11).getNumericCellValue();
//                    HSSFCell energyLevelCell = row.getCell(12);
//                    energyLevelCell.setCellType(CellType.STRING);
//                    String energyLevel = row.getCell(12) == null ? "" : energyLevelCell.getStringCellValue();
//                    // 获取图片数据
//                    List<PictureData> pictureDataList = picMap.get(String.valueOf(i));
//                    String paths = "";
//                    setHouse(0L, image, insertHouseList, postalCode, city, street, houseName, roomNumber, houseRent, detailAddress, bedroomCount, toiletCount, furniture, sellingPoint, serviceAmount, energyLevel, pictureDataList, paths);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            throw new Exception(HttpStatus.ERROR,"仅支持xlsx和xls文件");
//        }
//        return houseMapper.insertBatch(insertHouseList);
//    }
//    // 设置实体类数据
//    private void setHouse(Long userId, List<String> image, List<House> insertHouseList, String postalCode, String city,
//                          String street, String houseName, String roomNumber, double houseRent, String detailAddress,
//                          int bedroomCount, int toiletCount, String furniture, String sellingPoint, double serviceAmount,
//                          String energyLevel, List<PictureData> pictureDataList, String paths) throws IOException {
//        if (pictureDataList != null) {
//            for (PictureData pictureData : pictureDataList) {
//                String suggestFileExtension = pictureData.suggestFileExtension();// 图片格式
//                String s = UUID.fastUUID().toString().replaceAll("-", "");
//                String path = "D://home//uploadPath//upload//house//" + s + "."
//                        + suggestFileExtension;// 存储路径
//                paths = paths + path;
//                FileOutputStream out = new FileOutputStream(path);// 流写入
//                out.write(pictureData.getData());
//                out.close();
//                image.add("/profile/upload/house/" + s+"."+suggestFileExtension);
//            }
//        }
//        // 创建House对象
//        String join = StringUtils.join(image, ",");
//        House house = new House();
//        house.setCity(city);
//        house.setHouseImage(join);
//        house.setPostalCode(postalCode);
//        house.setFurniture(furniture);
//        house.setServiceAmount(new BigDecimal(serviceAmount));
//        house.setStreet(street);
//        house.setRoomNumber(roomNumber);
//        house.setHouseName(houseName);
//        house.setToiletCount(toiletCount);
//        house.setBedroomCount(bedroomCount);
//        house.setHouseRent(new BigDecimal(houseRent));
//        house.setDetailAddress(detailAddress);
//        getSafeness(house);
//        house.setSellingPoint(sellingPoint);
//        house.setEnergyLevel(energyLevel);
//        getLocation(house);
//        house.setCreator(userId);
//        house.setUpdater(userId);
//        image.clear();
//        insertHouseList.add(house);
//    }
//}
