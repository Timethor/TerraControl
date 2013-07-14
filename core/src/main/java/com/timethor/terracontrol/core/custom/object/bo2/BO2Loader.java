package com.timethor.terracontrol.core.custom.object.bo2;

import com.timethor.terracontrol.core.custom.object.CustomObject;
import com.timethor.terracontrol.core.custom.object.CustomObjectLoader;

import java.io.File;

public class BO2Loader implements CustomObjectLoader {

    public CustomObject loadFromFile(String objectName, File file) {
        return new BO2(file, objectName);
    }

    @Override
    public void onShutdown() {
        // Stub method
    }
}
