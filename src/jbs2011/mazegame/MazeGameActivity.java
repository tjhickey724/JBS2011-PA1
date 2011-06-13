package jbs2011.mazegame;


import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.os.Handler;

import jbs2011.tjhickey.maze.*;

/**
 * This is a simple graphical interface to the maze game which shows
 * MazePlayers competing against each other. There is no human interaction
 * yet but this is good for debugging ...
 * @author tim
 *
 */
public class MazeGameActivity
extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MazeView(this));
    }
    

    /*
     * this draws a 20x20 grid
     */
    private static class MazeView extends View {
        private Paint[] mPaints;
        private Paint mFramePaint;
        private MazeBoard board;
        private MazeGame g;
        
        /* these variables specify how quickly the players move about the screen */
        private static final int MOVE_MSG=1;
        private static final int MOVE_DELAY=1;
        
        /* these give the inter-line spacing and margin of the maze */
		private int dx=15;
		private int dy=15;
		private int margin=20;
		
		private Bitmap mBitmap ;

		/* this hander moves the players on the board and then "sleeps" for MOVE_DELAY ms before repeating... 
		 * This creates an animation that doesn't require a separate thread to be explicitly created
		 */
        private Handler mHandler = new Handler() {
            @Override public void handleMessage(Message msg) {
                switch (msg.what) {
                    // Upon receiving the fade pulse, we have the view perform a
                    // fade and then enqueue a new message to pulse at the desired
                    // next time.
                    case MOVE_MSG: {
                        movePlayers();
                        invalidate();
                        mHandler.sendMessageDelayed(
                                mHandler.obtainMessage(MOVE_MSG), MOVE_DELAY);
                        break;
                    }
                    default:
                        super.handleMessage(msg);
                }
            }
        };
        
        /**
         * a maze view creates two players, generates a board, and starts them playing
         * @param context
         */
        public MazeView(Context context) {
            super(context);
                   
            createPaints();
            
            // create the gameboard
            g = new MazeGame(10,10);
            board = g.theBoard;	
            
            // next we draw the board onto the mBitmap
            // by creating a canvas backed by the Bitmap and drawing on the canvas
            mBitmap = Bitmap.createBitmap(400,600,Bitmap.Config.RGB_565);
            Canvas boardCanvas = new Canvas(mBitmap);
            boardCanvas.drawColor(Color.WHITE);
            drawBoard(boardCanvas);
            createPlayers();


    	  mHandler.sendMessageDelayed(
                  mHandler.obtainMessage(MOVE_MSG), MOVE_DELAY);

        }
        
        private void createPlayers(){
            // add players to the board
      	  g.addPlayer( new jbs2011.tjhickey.maze.RandomPlayer("Rand"));
//      	  g.addPlayer( new jbs2011.tjhickey724.maze.MinDistPlayer("MD2"));

//        	  g.addPlayer( new jbs2011.taha.maze.AdvancedPlayerByTaha("Taha"));
        	  g.addPlayer( new jbs2011.jbenow.maze.JBPlayer1("JB"));
        	  //g.addPlayer( new jbs2011.acsuit.maze.Voldemort("AC"));
        	  //g.addPlayer( new jbs2011.gaspar.maze.GasparPlayer2("GP"));
        	  g.addPlayer( new jbs2011.jcrollai.JCrollPlayerPLvL1("JC"));
        	 // g.addPlayer( new jbs2011.jsoued.maze.JsouedPlayer2("JS2"));
        	  g.addPlayer( new jbs2011.mfieldai.MFieldDOMINATOR("MF"));
        	  //g.addPlayer( new jbs2011.MichaelsPlayers.cleverMichael("MW"));
        	  g.addPlayer( new jbs2011.sahar.maze.SaharBetterPlayer("SM"));
        	  g.addPlayer( new jbs2011.taha.maze.AdvancedPlayerByTaha("Taha"));


        }
        
        private void createPaints(){
            mPaints = new Paint[4];
            
            mPaints[0] = new Paint();
            mPaints[0].setAntiAlias(true);
            mPaints[0].setStyle(Paint.Style.FILL);
            mPaints[0].setColor(0xFFFF0000);
            
            mPaints[1] = new Paint();
            mPaints[1].setAntiAlias(true);
            mPaints[1].setStyle(Paint.Style.FILL);
            mPaints[1].setColor(0xFF00FF00);
            
            mPaints[2] = new Paint();
            mPaints[2].setAntiAlias(true);
            mPaints[2].setStyle(Paint.Style.FILL);
            mPaints[2].setColor(0xFF0000FF);

        }
 
        
/**
 * This blanks the screen, draws the board, draws the jewels and players
 * and writes the players scores at the bottom 
 */
        @Override protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLUE);           
    		//drawBoard(canvas);
            canvas.drawBitmap(mBitmap, 0, 0, mPaints[0]);
    		drawJewels(canvas);
    		drawPlayers(canvas);        
            invalidate();
        }
        
        
        private void drawJewels(Canvas canvas){
        	  //draw the jewels
            for (MazePosition p:g.jewelPosition){
            	int left = p.row*dx+margin+2;
            	int bottom = board.getDepth()*dy - p.col*dy+margin+2;
            	canvas.drawRect(left, bottom+dy-4, left+dx-4, bottom, mPaints[1]);
            }
        }
        
        private void drawPlayers(Canvas canvas){
        	  
            // draw the players and their scores!
            int counter=0;
            for (String s:g.playerPosition.keySet()){
            	counter = counter+1;
            	MazePosition p = g.playerPosition.get(s);
            	int left = p.row*dx+margin+2;
            	int bottom = board.getDepth()*dy - p.col*dy+margin+2 ;
            	canvas.drawRect(left, bottom+dy-4, left+dx-4, bottom, mPaints[2]);
            	canvas.drawText(s,left, bottom, mPaints[2]);
            	canvas.drawText(
            			s+": "+g.score.get(s),
            			100,
            			(board.getDepth()+2+2*counter)*dy,
            			mPaints[2]);
            }
        }
        
        /*
         * this draws the board on the canvas, but doesn't invalidate
         * as we usually want to add players and jewels before redrawing on the device!
         */
        private void drawBoard(Canvas canvas) {
        	//draw the board
            for (int i = 0; i < board.getWidth(); i++) {
            	for (int j=0; j<board.getDepth(); j++) {
            		MazePosition p = new MazePosition(i,j);

            		int xval = i*dx+margin;
            		int yval = board.getDepth()*dy - j*dy + margin;
            		if (!board.canMove(p,Direction.NORTH))
            			canvas.drawLine(xval,yval,xval+dx,yval,mPaints[0]);
            		if (!board.canMove(p,Direction.SOUTH))
            			canvas.drawLine(xval,yval+dy,xval+dx,yval+dy,mPaints[0]);
            		if (!board.canMove(p,Direction.EAST))
            			canvas.drawLine(xval+dx,yval,xval+dx,yval+dy,mPaints[0]);
            		if (!board.canMove(p,Direction.WEST))
            			canvas.drawLine(xval,yval,xval,yval+dy,mPaints[0]);
            			
            		}
            	}
        }
        
        private void movePlayers() {
    	    for (MazePlayer p: g.player.values()){
    	    	Direction d = p.nextMove(g.playerPosition,g.jewelPosition,g.theBoard);
    	    	g.movePlayer(p,d);
    	    
    	    }
        }
        
        /**
         * if the user touches the screen then move the players and redraw the board
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	movePlayers();
                    invalidate();

                    break;
                case MotionEvent.ACTION_MOVE:

                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    invalidate();
                    break;
            }
            return true;
        }
 
    
    
    /**
     * this allows a user to move the players by pressing the left arrow key
     * but this only make sense if the handler is not being used ...
     * We need a pause feature to stop the players from moving automatically
     * and allow them to be single-stepped by the user
     */
    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
            	movePlayers();
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:

                return true;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

  }  
    
}