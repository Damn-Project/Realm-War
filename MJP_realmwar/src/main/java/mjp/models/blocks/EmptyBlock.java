package mjp.models.blocks;

import mjp.models.Position;

import java.awt.*;

public class EmptyBlock extends Block{

    public EmptyBlock(Position position) {
        super(position);
        color = Color.WHITE;
        setBackground(color);
    }
}
