import React, { useEffect } from "react";

import { useState } from "react";
import "./Matrix.css";
import { fetchTopLocations, fetchNextLocation } from "./services/requests/Api.js"

const LocationTree = ({ locations, onSelectLocation }) => {
  const handleLocationClick = (location) => {
    console.log('Выбранная локация:', location.name);
    onSelectLocation(location);
  };

  const renderLocation = (location) => {
    return (
      <div key={location.id} onClick={() => handleLocationClick(location)} style={{ cursor: "pointer" }}>
        {location.name}
        {location.children && renderLocations(location.children)}
      </div>
    );
  };

  const renderLocations = (locations) => {
    return (
      <div style={{ marginLeft: "20px" }}>
        {locations.map((location) => renderLocation(location))}
      </div>
    );
  };

  return (
    <div>
      {renderLocations(locations)}
    </div>
  );
};

const TreeLocation = () => {
  const [topLevelLocations, setTopLevelLocations] = useState([]);
  const [selectedLocation, setSelectedLocation] = useState(null);

  useEffect(() => {
    // Функция для получения верхнего уровня локаций
    fetchTopLocations()
      .then(locations => {
        setTopLevelLocations(locations);
      })
      .catch(error => {
        console.error('Ошибка при загрузке верхнего уровня локаций:', error);
      });
  }, []);

  // Функция для загрузки всех дочерних локаций по уровню
  const fetchAllLocationsByLevel = async (location) => {
    if (!location.child_nodes || location.child_nodes.length === 0) return;
    const nextLocations = await fetchNextLocation(location.location_id);
    if (!nextLocations || nextLocations.length === 0) return;

    // Рекурсивно получаем всех дочерних локаций
    for (const childLocation of nextLocations) {
      await fetchAllLocationsByLevel(childLocation);
    }

    // Обновляем список всех локаций по уровню
    setTopLevelLocations([...topLevelLocations, ...nextLocations]);
  };

  // Обработчик выбора локации
  const handleSelectLocation = async (location) => {
    setSelectedLocation(location);

    // Получаем все дочерние локации по уровню
    await fetchAllLocationsByLevel(location);
  };

  return (
    <>
      <div className="Matrix">
        {/* Отображаем дерево верхнего уровня локаций */}
        <LocationTree locations={topLevelLocations} onSelectLocation={handleSelectLocation} />
        
        {/* Ваш остальной код */}
      </div>
    </>
  );
};

export default TreeLocation;
