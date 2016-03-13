package grapheditor.controler.mouse;

import java.lang.reflect.InvocationTargetException;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.event.MouseInputListener;

import catchers.ReflectionCatcher;
import grapheditor.view.main.PaintingPanel;

public class GraphControlerFactory {
	private Map<String, EventListener> controlers = null;

	private static GraphControlerFactory factory = null;

	private Properties prop = null;

	private GraphControlerFactory() {
		createProperty();
	}

	static public GraphControlerFactory getInstance() {
		if (factory == null) {
			factory = new GraphControlerFactory();
		}
		return factory;
	}

	private void createProperty() {
		prop = new Properties();
		prop.setProperty("Node_tool", "grapheditor.controler.mouse.NodeEditor");
		prop.setProperty("Arc_tool", "grapheditor.controler.mouse.ArcEditor");
		prop.setProperty("ShapedComponent", "grapheditor.controler.mouse.SCMouseListener");
	}

	public MouseInputListener getMouseInputListener(String s, PaintingPanel ui) {
		if (controlers == null) {
			controlers = new HashMap<String, EventListener>();
		}
		MouseInputListener listener = (MouseInputListener) controlers.get(s);
		if (listener == null) {
			if (prop == null) {
				createProperty();
			}
			String name = prop.getProperty(s);
			
				try {
					listener = (MouseInputListener) Class.forName(name).getConstructor(ui.getClass()).newInstance(ui);
				} catch (ClassNotFoundException e) {
					ReflectionCatcher.classNotFound(e);

				}
				catch(InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException
						e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(listener != null){
				controlers.put(s, listener);
			}
		}
		return listener;
	}
	public MouseInputListener getMouseInputListener(String s) {
		if (controlers == null) {
			controlers = new HashMap<String, EventListener>();
		}
		MouseInputListener listener = (MouseInputListener) controlers.get(s);
		if (listener == null) {
			if (prop == null) {
				createProperty();
			}
			String name = prop.getProperty(s);
			
				try {
					listener = (MouseInputListener) Class.forName(name).newInstance();
				} catch (ClassNotFoundException e) {
					ReflectionCatcher.classNotFound(e);

				}
				catch(InstantiationException | IllegalAccessException | IllegalArgumentException
						| SecurityException
						e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(listener != null){
				controlers.put(s, listener);
			}
		}
		return listener;
	}
}