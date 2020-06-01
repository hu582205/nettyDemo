package io.test.proto;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 测试数据信息
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-01
 */
public class TestDataInfo {

	public static void main(String[] args) throws InvalidProtocolBufferException {
		DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAddress("中国").setAge(1).build();
		byte[] studentByteArray = student.toByteArray();

		DataInfo.Student student1 = DataInfo.Student.parseFrom(studentByteArray);

		System.out.println(student1.getName());
		System.out.println(student1.getAddress());
		System.out.println(student1.getAge());
	}
}
