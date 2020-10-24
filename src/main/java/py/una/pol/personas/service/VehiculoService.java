/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package py.una.pol.personas.service;


import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.personas.dao.VehiculoDAO;
import py.una.pol.personas.model.Vehiculo;

import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class VehiculoService {

    @Inject
    private Logger log;

    @Inject
    private VehiculoDAO dao;

    public void crear(Vehiculo v) throws Exception {
        log.info("Creando vehiculo: " + v.getidVehiculo() + " " + v.getTipo());
        try {
        	dao.insertar(v);
        }catch(Exception e) {
        	log.severe("ERROR al crear vehiculo: " + e.getLocalizedMessage() );
        	throw e;
        }
        log.info("Vehiculo creada con éxito: " + v.getidVehiculo() + " " + v.getTipo());
    }
    
    public List<Vehiculo> seleccionar() {
    	return dao.seleccionar();
    }
    
    public Vehiculo seleccionarPorID(int id_vehiculo) {
    	return dao.seleccionarPorId(id_vehiculo);
    }
    
    public long borrar(int id_vehiculo) throws Exception {
    	return dao.borrar(id_vehiculo);
    }
}
