package com.semillero.ubuntu.Config;

import com.semillero.ubuntu.DataSeed.MicroemprendimientoDataLoader;
import com.semillero.ubuntu.DataSeed.PaisProvinciaDataLoader;
import com.semillero.ubuntu.DataSeed.RubroDataLoader;
import com.semillero.ubuntu.DataSeed.UsuarioDataLoader;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeedConfig implements SmartInitializingSingleton {
    private final PaisProvinciaDataLoader paisProvinciaDataLoader;
    private final RubroDataLoader rubroDataLoader;
    private final UsuarioDataLoader usuarioDataLoader;
    private final MicroemprendimientoDataLoader microemprendimientoDataLoader;
    public DataSeedConfig(PaisProvinciaDataLoader paisProvinciaDataLoader,
                          RubroDataLoader rubroDataLoader,
                          UsuarioDataLoader usuarioDataLoader,
                          MicroemprendimientoDataLoader microemprendimientoDataLoader) {
        this.paisProvinciaDataLoader = paisProvinciaDataLoader;
        this.rubroDataLoader = rubroDataLoader;
        this.usuarioDataLoader = usuarioDataLoader;
        this.microemprendimientoDataLoader = microemprendimientoDataLoader;
    }
    @Override
    public void afterSingletonsInstantiated() {
        paisProvinciaDataLoader.loadPaisData();
        rubroDataLoader.loadRubroData();
        usuarioDataLoader.loadUsuarioData();
        microemprendimientoDataLoader.loadMicroemprendimientoWithImages();
    }
}