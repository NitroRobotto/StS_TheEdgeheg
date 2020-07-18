package theEdgeheg.util;

import basemod.animations.AbstractAnimation;

public class NullAnimation extends AbstractAnimation {

    @Override
    public Type type() {
        return Type.NONE;
    }
}
