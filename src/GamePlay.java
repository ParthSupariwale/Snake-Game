import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;

public class GamePlay extends JPanel implements ActionListener, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1500000000000L;
	private int[] snakexlength = new int[900];
	private int[] snakeylength = new int[900];
	private int lengthofsnake = 3;
	private int moves = 0;
	private int score = 0;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private Random random = new Random();
	private int applex = (random.nextInt(34) * 25) + 25;
	private int appley = (random.nextInt(23) * 25) + 75;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakebody;
	private ImageIcon titleImage;
	private ImageIcon apple;
	
	private Timer timer;
	private int delay = 100;
	
	
	
	public GamePlay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//displays_snake_at_start;
		if(moves==0) {
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		
		//title_border;
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//title_image;
		titleImage = new ImageIcon("snaketitle_new5.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//frame_border;
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 576);
		
		//frame_color;
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//rightmouth_image;
		rightmouth = new ImageIcon("rightsnakehead_new.jpg");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		//for_diff_dircn_snake_head;
		for(int i=0; i<lengthofsnake; i++) {
			
			//rigth_direcn_head;
			if(i==0 && right) {
				rightmouth = new ImageIcon("rightsnakehead_new.jpg");
				rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			//left_direcn_head;
			if(i==0 && left) {
				leftmouth = new ImageIcon("leftsnakehead_new.jpg");
				leftmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			//up_direcn_head;
			if(i==0 && up) {
				upmouth = new ImageIcon("upsnakehead_new.jpg");
				upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			//down_direcn_head;
			if(i==0 && down) {
				downmouth = new ImageIcon("downsnakehead.jpg");
				downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
			
			//snake_body;
			if(i!=0) {
				snakebody =new ImageIcon("snakebodypart_new.jpg");
				snakebody.paintIcon(this, g, snakexlength[i], snakeylength[i]);
			}
		}
		
		//apple;
		apple = new ImageIcon("apple.jpg");
		apple.paintIcon(this, g, applex, appley);
		if(applex == snakexlength[0] && appley == snakeylength[0]) {
			lengthofsnake++;
			score++;
			applex = (random.nextInt(34) * 25) + 25;
			appley = (random.nextInt(23) * 25) + 75;
		}
		
		//score;
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 16));
		g.drawString("Score: " + score, 785, 45);
		
		//game_over;
		for(int i=1; i<lengthofsnake; i++) {
			if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				lengthofsnake = 3;
				score = 0;
				
				g.setColor(Color.yellow);
				g.setFont(new Font("arial", Font.BOLD, 40));
				g.drawString("Press SPACE to Restart", 225, 350);
				
				JOptionPane.showMessageDialog(null, "Game Over");
			}
		}
		
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if (applex == snakexlength[0] && appley == snakeylength[0]) {
		    lengthofsnake++;
		    applex = (random.nextInt(34) * 25) + 25;
		    appley = (random.nextInt(23) * 25) + 75;
		}
		
		if(right) {
			for (int i = lengthofsnake - 1; i >= 0; i--) {
		        snakeylength[i + 1] = snakeylength[i];
		        snakexlength[i + 1] = snakexlength[i];
		    }
		    snakexlength[0] = snakexlength[0] + 25;
		    if (snakexlength[0] > 850) {
		        snakexlength[0] = 25;
		    }
		    repaint();
		}
		
		if(left) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeylength[i+1] = snakeylength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakexlength[i] = snakexlength[i] - 25;
				}
				else {
					snakexlength[i] = snakexlength[i-1];
				}
				if(snakexlength[i] < 25) {
					snakexlength[i] = 850;
				}
			}
			repaint();
		}
		
		if(up) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakexlength[i+1] = snakexlength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakeylength[i] = snakeylength[i] - 25;
				}
				else {
					snakeylength[i] = snakeylength[i-1];
				}
				if(snakeylength[i] < 75) {
					snakeylength[i] = 625;
				}
			}
			repaint();
		}
		
		if(down) {
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakexlength[i+1] = snakexlength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				if(i==0) {
					snakeylength[i] = snakeylength[i] + 25;
				}
				else {
					snakeylength[i] = snakeylength[i-1];
				}
				if(snakeylength[i] > 625) {
					snakeylength[i] = 75;
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//for restart;
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			repaint();
		}
		
		//for_right_direcn;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left= true;
			}
			up = false;
			down = false;
		}
		
		//for_left_direcn;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right= true;
			}
			up = false;
			down = false;
		}
		
		//for_up_direcn;
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down= true;
			}
			right = false;
			left = false;
		}
		
		//for_down_direcn;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				down = false;
				up= true;
			}
			right = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
	}

	
}
