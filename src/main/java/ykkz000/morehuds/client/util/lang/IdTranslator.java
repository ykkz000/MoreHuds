package ykkz000.morehuds.client.util.lang;

import ykkz000.morehuds.client.MoreHudsClient;
import net.minecraft.text.Text;

public class IdTranslator {
    public static String translate(String id) {
        return Text.translatable(id).getString();
    }

    public static String translate(String type, String namespace, String id) {
        return translate(String.format("%s.%s.%s", type, namespace, id));
    }

    public static String translateString(String namespace, String id) {
        return translate("string", namespace, id);
    }

    public static String translateString(String id) {
        return translate("string", MoreHudsClient.MOD_ID, id);
    }
}
