package alex.WorldExplorer;

public class World {
    // 2d matrix of tiles
    Tile[][] worldTiles;

    public World() {
         worldTiles = new Tile[10][10];
    }

    public void moveWorld() {
        for(int i = 0;i < worldTiles.length;i++) {
            for(int g = 0;i < worldTiles[i].length;i++) {
                worldTiles[i][g].move(1, 1);
            }
        }
    }


}
