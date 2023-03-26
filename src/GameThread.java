public class GameThread extends Thread{
    private final GameArea ga;
    public GameThread(GameArea ga){
        this.ga = ga;
    }
    @Override
    public void run() {
        while(true) {
            //System.out.println("spawning a block");

            while(ga.moveBlockDown()) {       //beautiful construct, both moving and inside loop returning boolean
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("the block is down");
            ga.spawnBlock();
            if(ga.checkCompleteLines()){
                try {
                    ga.makeCompleteLinesLighter();
                    Thread.sleep(150);
                    ga.makeCompleteLinesDarker();
                    Thread.sleep(150);
                    ga.makeCompleteLinesLighter();
                    Thread.sleep(150);
                    ga.makeCompleteLinesDarker();
                    Thread.sleep(150);
                    ga.makeCompleteLinesLighter();
                    ga.removeCompleteLines();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
