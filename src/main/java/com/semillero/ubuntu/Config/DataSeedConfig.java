package com.semillero.ubuntu.Config;

import com.semillero.ubuntu.DataSeed.*;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeedConfig implements SmartInitializingSingleton {
    private final PaisProvinciaDataLoader paisProvinciaDataLoader;
    private final RubroDataLoader rubroDataLoader;
    private final UsuarioDataLoader usuarioDataLoader;
    private final MicroemprendimientoDataLoader microemprendimientoDataLoader;
    private final ImagePublicacionDataLoader imagePublicacionDataLoader;
    public DataSeedConfig(PaisProvinciaDataLoader paisProvinciaDataLoader,
                          RubroDataLoader rubroDataLoader,
                          UsuarioDataLoader usuarioDataLoader,
                          MicroemprendimientoDataLoader microemprendimientoDataLoader,
                          ImagePublicacionDataLoader imagePublicacionDataLoader)
    {
        this.paisProvinciaDataLoader = paisProvinciaDataLoader;
        this.rubroDataLoader = rubroDataLoader;
        this.usuarioDataLoader = usuarioDataLoader;
        this.microemprendimientoDataLoader = microemprendimientoDataLoader;
        this.imagePublicacionDataLoader= imagePublicacionDataLoader;
    }
    @Override
    public void afterSingletonsInstantiated() {
        paisProvinciaDataLoader.loadPaisData();
        rubroDataLoader.loadRubroData();
        usuarioDataLoader.loadUsuarioData();
        microemprendimientoDataLoader.loadMicroemprendimientoWithImages();
        imagePublicacionDataLoader.loadImageAndPublicacionData();
    }
}