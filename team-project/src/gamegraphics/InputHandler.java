package gamegraphics;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Obsolete for now. Let's keep it in case it becomes useful later.
 * @author ibs483
 *
 */

public class InputHandler implements KeyListener, MouseListener {

	// boolean array representing whether a certain key has been pressed
	private boolean[] keyArray = new boolean[256];
	// boolean array representing whether a mouse button has been pressed
	private boolean[] buttonArray = new boolean[MouseInfo.getNumberOfButtons()];
	// test you whether the mouse is over the component
	private boolean overComp;
	// the component
	private Component c;

	public InputHandler(Component c) {
		// adds the key and button listeners to the component
		this.c = c;
		c.addKeyListener(this);
		c.addMouseListener(this);

	}

	/**
	 * check whether a key has been pressed
	 * 
	 * @param keyCode
	 *            the code of the key to be checked
	 * @return yes or no
	 */
	public boolean isKeyDown(int keyCode) {
		try {
			return keyArray[keyCode];
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid index.");
		}

		return false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		try {
			System.out.println("Pressed!");
			keyArray[arg0.getKeyCode()] = true;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		try {
			System.out.println("Released!");
			keyArray[arg0.getKeyCode()] = false;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * get the coordinates of the mouse on the screen relative to the component
	 * 
	 * @return
	 */
	public Point getMousePosition() {
		try {
			Point p = c.getMousePosition();

			if (p.equals(null)) {
				p = new Point();
				return p;
			} else {
				return p;
			}

		} catch (Exception e) {
			System.out.println("Out of bounds");
			return MouseInfo.getPointerInfo().getLocation();
		}
	}

	/**
	 * check if a mouse button has been pressed
	 * 
	 * @param button
	 *            the code of the button to be checked
	 * @return yes or no
	 */
	public boolean isMouseDown(int button) {
		return buttonArray[button];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// overComp = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		overComp = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		overComp = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonArray[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttonArray[e.getButton()] = false;
	}
}
