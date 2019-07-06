
package tetrisfinal;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
public class renderertetris implements GLEventListener, KeyListener{
    
    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private double x;
    private double y;
    private double y2;
    private double x2;
    private boolean moverAbajo2 = true;
    private int k=0;    
//    private io limx;
//    private io limy;

    public renderertetris() {
        glu = new GLU();
        glut = new GLUT();
        x=0;
        y=9;
        y2 = 9;
        x2=0;
    }
        
    @Override
    public void init(GLAutoDrawable drawable) {
//        limx= new io();
//        limy=new io();        
        gl = drawable.getGL().getGL2();
        // Recuperar el ancho y alto de ventama de visualización
        int w = ((Component) drawable).getWidth();
        int h = ((Component) drawable).getHeight();
        //recuperar las coordenadas limite
        int limx=((Component)drawable).getX();
        int limy=((Component)drawable).getY();
        System.out.println(limx+" "+limy);
        // Establecer un visor en todo el area de la ventana de visualización
        gl.glViewport(0, 0, limx, limy);
        // Establecer el uso de matrices en Modelo-Vista
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Carga la matriz identidad
        // gl.glLoadIdentity();
        // Posición de la camara
        glu.gluLookAt(
                0, -1, 19.5,
                0, 0 , 2   ,
                0, 1 , 0   
        );
        // Habilitar el Buffer de colores RGB
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // Aspect is width/height
        double aspect = w / h;
        glu.gluPerspective(60.0, aspect, 2.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
//        gl.glLoadIdentity();
    }
    
    @Override
    public void display(GLAutoDrawable drawable) {
        System.out.println("display");
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT |
                GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0, 0, 0, 1);
        dibujartablero(gl);
        dibujarcubo(gl,x2,y2);        
        //Controlar la colision con los bordes
        desplazamientoabajo(gl);        
    }
    
    public void desplazamientoabajo(GL2 gl){
        if(moverAbajo2){
//            do{y2=y2-1;
//            }while(y2<-9);
//            
            if( y2>-8){
                y2--;
//                if(y2==-8){
//                    y2=0;
//                    dibujarcubo(gl,x,y);
//                }
            }else{System.out.println("termino");}
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(renderertetris.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("Y2: " + y2);
    }
    public void dibujarcubo(GL2 gl,double x2,double y2){
        //cubo
        gl.glPushMatrix();
        gl.glColor3f(1, 0, 1);             
        gl.glTranslated(x2,y2, 0);
//       gl.glRotated(45, 0, 0, 1);
        glut.glutSolidCube(1);
        gl.glPopMatrix();
    }
    public void dibujartablero(GL2 gl){
        // Posiciona y muestra una esfera alámbrica
        //gl.glTranslated(-5,4, 0);
        for(int i=-9;i<11;i++){
             gl.glPushMatrix();
        gl.glColor3f(1, 0, 1);             
        gl.glTranslated(-6,i, 0);
//        gl.glRotated(45, 0, 0, 1);
//        glut.glutWireCube(1);
        glut.glutWireCube(1);
        gl.glPopMatrix();
        }
        for(int i=-9;i<11;i++){
             gl.glPushMatrix();
        gl.glColor3f(1, 0, 1);             
        gl.glTranslated(6,i, 0);
//        gl.glRotated(45, 0, 0, 1);
        glut.glutWireCube(1);
        gl.glPopMatrix();
        }
        for(int i=-6;i<6;i++){
             gl.glPushMatrix();
        gl.glColor3f(1, 0, 1);             
        gl.glTranslated(i,10, 0);
//        gl.glRotated(45, 0, 0, 1);
        glut.glutWireCube(1);
        gl.glPopMatrix();
        }
        for(int i=-6;i<6;i++){
             gl.glPushMatrix();
        gl.glColor3f(1, 0, 1);             
        gl.glTranslated(i,-9, 0);
//        gl.glRotated(45, 0, 0, 1);
        glut.glutWireCube(1);
        gl.glPopMatrix();
        }
       
    }
    
    @Override
    public void dispose(GLAutoDrawable glad) {
        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
//       System.out.println("reshape");  
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
         switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT : {
                x2++;
//                System.out.println(" X: "+x2);
                System.out.println("derecha");
                if(x2>5){
                    x2=5;
                }
                break;
            } 
         case KeyEvent.VK_LEFT : {
                x2--;
//                System.out.println(" X: "+x2);
                System.out.println("izquierda");
                if(x2<-5 ){
                    x2=-5;
                }
                break;
            }}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
