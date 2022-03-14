### 新增/修改房间   Post    /saveRoom

| Request        |             |                      |
| -------------- | ----------- | -------------------- |
| **roomNumber** | **string**  | **房间编号**         |
| **roomType**   | **String**  | **房间类型**         |
| **location**   | **String**  | **房间所在楼层**     |
| **roomStatus** | **Integer** | **房间状态**         |
| **size**       | **Integer** | **房间可住人数**     |
| **createdBy**  | **String**  | **创建人 (用户名) ** |
| **price**      | **Integer** | **房价**             |
| **facilicy**   | **String**  | **房间设施**         |
| **imageUrl**   | **String**  | **对应图片的地址**   |



### 删除房间  Post  /deleteRoom

| Request |            |                  |
| ------- | ---------- | ---------------- |
| **Id**  | **String** | **房间唯一标识** |



### 批量删除房间  Post  /batchDeleteRoom

| Request |                    |                            |
| ------- | ------------------ | -------------------------- |
| **Ids** | **List\<String\>** | **房间唯一标识  数组类型** |



### 获取房间列表  Get  /getRooms

| Response       | List类型    |                      |
| -------------- | ----------- | -------------------- |
| **Id**         | **String**  | **房间唯一标识**     |
| **roomNumber** | **string**  | **房间编号**         |
| **roomType**   | **String**  | **房间类型**         |
| **location**   | **String**  | **房间所在楼层**     |
| **roomStatus** | **Integer** | **房间状态**         |
| **size**       | **Integer** | **房间可住人数**     |
| **createdBy**  | **String**  | **创建人 (用户名) ** |
| **price**      | **Integer** | **房价**             |
| **facilicy**   | **String**  | **房间设施**         |
| **imageUrl**   | **String**  | **对应图片的地址**   |



### 获取图片  Get  /getImage   接口待定

| Request      |            |                   |
| ------------ | ---------- | ----------------- |
| **iamgeUrl** | **String** | **对应图片的URL** |



### 添加入住信息  Post  /addCheckIn

| Request         | 请求参数           |                         |
| --------------- | ------------------ | ----------------------- |
| **roomNumber**  | **String**         | **房间编号**            |
| **registrant**  | **String**         | **开房人**              |
| **cashPledge**  | **Integer**        | **押金**                |
| **shouldPay**   | **Integer**        | **应缴纳金额**          |
| **realPay**     | **Integer**        | **实际缴纳金额**        |
| **inDate**      | **dateTime**       | **入住时间**            |
| **outDate**     | **dateTime**       | **离店时间**            |
| **createBy**    | **String**         | **录入人信息 (用户名)** |
| **checkInUser** | **List\<custom\>** | **入住人信息 数组格式** |

| Custom       | 入住人信息对象 |                |
| ------------ | -------------- | -------------- |
| **name**     | **String**     | **入住人姓名** |
| **idNumber** | **String**     | **身份证号**   |
| **phone**    | **String**     | **手机号**     |

- **入住人数量不大于房间最大容量**
- **仅房间状态为空闲中可添加入住信息 (前端限制按钮不可点击)**



### 查询入住信息  Get  /getCheckIn 	

| Request        | 请求参数   |              |
| -------------- | ---------- | ------------ |
| **roomNumber** | **String** | **房间编号** |

| Response        | 返回参数           |                         |
| --------------- | ------------------ | ----------------------- |
| **registrant**  | **String**         | **开房人**              |
| **cashPledge**  | **Integer**        | **押金**                |
| **shouldPay**   | **Integer**        | **应缴纳金额**          |
| **realPay**     | **Integer**        | **实际缴纳金额**        |
| **inDate**      | **dateTime**       | **入住时间**            |
| **outDate**     | **dateTime**       | **离店时间**            |
| **createBy**    | **String**         | **录入人信息 (用户名)** |
| **checkInUser** | **List\<custom\>** | **入住人信息 数组格式** |

| Custom       | 入住人信息对象 |                |
| ------------ | -------------- | -------------- |
| **name**     | **String**     | **入住人姓名** |
| **idNumber** | **String**     | **身份证号**   |
| **phone**    | **String**     | **手机号**     |



### 更新房间状态  Post  /updateRoomStatus	由打扫中 ——> 空闲中

| Request        | 请求参数   |              |
| -------------- | ---------- | ------------ |
| **roomNumber** | **string** | **房间编号** |

- **仅房间状态为 打扫中 可触发接口  更新为空闲中**



### 退房  Post  /checkOut	

| Request        | 请求参数    |              |
| -------------- | ----------- | ------------ |
| **roomNumber** | **String ** | **房间编号** |

**接口功能：将入住信息删除，房间状态变更为打扫中。**



### 授权  Post  /authorize   *待定*