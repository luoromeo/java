package design.uranus.study.annotation.util;

import design.uranus.study.annotation.ann.RequestMapping;
import design.uranus.study.annotation.bean.ExecutorBean;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghua.luo on 12/30/16.
 */
@Slf4j
public class HandlerAnnotationFactoryBean {
	public static List<Class<?>> getPackageController(String packageName, Class<? extends Annotation> annotations) {
		List<Class<?>> classList = new ArrayList<>();
		classList = ClassUtil.getClasses(packageName);
		for (Class<?> clazz : classList) {
			if (null != clazz && null != clazz.getAnnotation(annotations)) {
				classList.add(clazz);
			}
		}
		return classList;
	}

	public static void getRequestMappingMethod(List<Class<?>> classList, Map<String, ExecutorBean> mpp) {
		for (Class classes : classList) {
			//得到该类下的所有方法
			Method[] methods = classes.getMethods();

			for (Method method : methods) {
				//得到该类下面的RequestMapping注解
				RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
				if (null != requestMapping) {
					ExecutorBean executorBean = new ExecutorBean();
					try {
						executorBean.setObject(classes.newInstance());

					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
					executorBean.setMethod(method);
					mpp.put(requestMapping.value(), executorBean);
				}
			}
		}
	}

	private static void getFilePathClasses(String packageName, String filePath, List<Class<?>> classList, Class<? extends Annotation> annotation) {
		Path dir = Paths.get(filePath);
		DirectoryStream<Path> stream = null;
		try {
			//获取当前目录下的文件的stream流
			stream = Files.newDirectoryStream(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Path path : stream) {
			if (path.toFile().isFile()) {
				String fileName = String.valueOf(path.getFileName());
				String className = fileName.substring(0, fileName.length() - 6);
				Class<?> classes = null;
				try {
					classes = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				//判断该注解是不是所需要的类型
				if (null != classes && null != classes.getAnnotation(annotation)) {
					classList.add(classes);
				}
			}
		}

	}

	private static void fetchFileList(File dir, List<File> fileList) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				fetchFileList(f, fileList);
			}
		} else {
			fileList.add(dir);
		}
	}
}
