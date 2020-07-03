package Java_反射.反射;

import java.lang.reflect.Field;

public class Test_通用的设置某个对象成员变量 {
	// 将obj对象中名为propertvName的属性的值设置为value
	public static void setPropertv(Object obj, String propertvName, Object value) {
		// 获取字节码文件对象
		Class<?> c = obj.getClass();
		try {
			// 获取名为propertvName的成员变量
			Field field = c.getDeclaredField(propertvName);
			// 暴力访问
			field.setAccessible(true);
			// 设置成员变量的值为value
			field.set(obj, value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
