public class GameThread extends Thread{
    private final GameArea ga;
    public GameThread(GameArea ga){
        this.ga = ga;
    }
    @Override
    public void run() {
        System.out.println("This is thread printing");
        while(true) {
            try {
                Thread.sleep(500);
                ga.moveBlockDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
