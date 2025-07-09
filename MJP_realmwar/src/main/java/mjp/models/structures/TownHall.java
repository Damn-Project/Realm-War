package mjp.models.structures;

import com.google.gson.Gson;
import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class TownHall extends Structure {

    public TownHall(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[9];
        health = 20;
        classType = "TownHall";
    }
}