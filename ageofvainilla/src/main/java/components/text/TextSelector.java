package components.text;


import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.events.constants.Key;
import scenes.FieldScene;

import java.util.ArrayList;

public class TextSelector extends GameComponent<FieldScene> {
    ArrayList<SelectableText> selectableTextArray;
    SelectableText current;

    public TextSelector(ArrayList<SelectableText> selectableTextArray) {
        this.selectableTextArray = selectableTextArray;
        if (selectableTextArray.size() > 0) {
            current = selectableTextArray.get(0);
        }
    }

    @Override
    public void update(DeltaState deltaState) {
        if (deltaState.isKeyReleased(Key.UP))
            this.previous();
        if (deltaState.isKeyReleased(Key.DOWN))
            this.next();
    }

    public void previous() {
        if (this.selectableTextArray.size() != 0 && selectableTextArray.indexOf(current) != 0) {
            current.unselect();
            current = selectableTextArray.get(selectableTextArray.indexOf(current) - 1);
            current.select();
        }
    }

    public void next(){
        if (this.selectableTextArray.size() != 0 && selectableTextArray.indexOf(current) != (this.selectableTextArray.size() - 1)) {
            current.unselect();
            current = selectableTextArray.get(selectableTextArray.indexOf(current) + 1);
            current.select();
        }
    }
}
