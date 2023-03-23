public class GameThread extends Thread{
    private final GameArea ga;
    public GameThread(GameArea ga){
        this.ga = ga;
    }
    @Override
    public void run() {
        while(true) {
            System.out.println("spawning a block");
            ga.spawnBlock();
            while(ga.moveBlockDown()) {       //beautiful construct, both moving, inside loop returning boolean
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
