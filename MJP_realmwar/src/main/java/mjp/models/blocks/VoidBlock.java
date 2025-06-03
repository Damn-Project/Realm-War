package mjp.models.blocks;

import mjp.models.Position;

import java.awt.*;

public class VoidBlock extends Block{

    public VoidBlock(Position position) {
        super(position);
        color = Color.LIGHT_GRAY;
        setBackground(color);
    }
}
