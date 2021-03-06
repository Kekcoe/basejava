import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

        public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume("Chmut","uuid");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        // TODO : invoke resume.toString via reflection
        System.out.println(resume);
            try {
                Method method = Resume.class.getMethod("toString");
                method.invoke(resume);
            }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
    }
}
