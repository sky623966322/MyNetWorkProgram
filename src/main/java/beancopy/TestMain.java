package beancopy;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author:wangyan1
 * @create_time: 2018-03-30 11:31
 */

public class TestMain {

	public static void main(String[] args) {
		FromBean fb = new FromBean();
		fb.setAddress("北京市朝阳区大屯路");
		fb.setAge(20);
		fb.setMoney(30000.111);
		fb.setIdno("110330219879208733");
		fb.setName("测试");
	}

	public static ToBean apacheBeanUtils(FromBean fromBean) throws Exception {
		ToBean toBean = new ToBean();
		BeanUtils.copyProperties(fromBean, toBean);
		return toBean;
	}

}
