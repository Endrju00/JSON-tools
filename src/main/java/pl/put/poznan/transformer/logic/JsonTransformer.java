package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorators.*;

import java.lang.reflect.Array;
import java.util.Arrays;

public class JsonTransformer {

    private String[] transforms;
    private String[] toSave;
    private String[] toCut;

    public JsonTransformer (String[] transforms, String[] toCut, String[] toSave) {
        this.transforms = transforms;
        this.toCut = toCut;
        this.toSave = toSave;
    }

    public String transform(Json data) {

        data = new JsonValidatorDecorator(data);

        for (String transform : transforms) {
            switch (transform) {
                case "minify":
                    data = new JsonMinifierDecorator(data);
                    break;
                case "clarify":
                    data = new JsonClarifierDecorator(data);
                    break;
                case "cut":
                    data = new JsonCutterDecorator(data, Arrays.asList(toCut));
                    break;
                case "save":
                    data = new JsonSaverDecorator(data, Arrays.asList(toSave));
                    break;
                default:
                    break; //throw
            }
        }
        return data.getData();
    }

}
