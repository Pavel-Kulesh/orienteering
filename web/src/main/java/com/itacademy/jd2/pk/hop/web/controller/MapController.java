package com.itacademy.jd2.pk.hop.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.pk.hop.dao.api.entity.IMap;
import com.itacademy.jd2.pk.hop.dao.api.entity.IRoute;
import com.itacademy.jd2.pk.hop.dao.api.entity.Track;
import com.itacademy.jd2.pk.hop.dao.api.filter.MapFilter;
import com.itacademy.jd2.pk.hop.service.IMapService;
import com.itacademy.jd2.pk.hop.service.IRouteService;
import com.itacademy.jd2.pk.hop.web.converter.MapFromDTOConverter;
import com.itacademy.jd2.pk.hop.web.converter.MapToDTOConverter;
import com.itacademy.jd2.pk.hop.web.dto.IdHolder;
import com.itacademy.jd2.pk.hop.web.dto.MapDTO;
import com.itacademy.jd2.pk.hop.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/map")
public class MapController extends AbstractController<MapDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);
    private IMapService mapService;
    private MapToDTOConverter toDTOConverter;
    private MapFromDTOConverter fromDTOConverter;
    private IRouteService routeService;

    @Autowired
    public MapController(IMapService mapService, MapToDTOConverter toDTOConverter, MapFromDTOConverter fromDTOConverter,
            IRouteService routeService) {
        super();
        this.mapService = mapService;
        this.toDTOConverter = toDTOConverter;
        this.fromDTOConverter = fromDTOConverter;
        this.routeService = routeService;

    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(final HttpServletRequest req,

            @RequestParam(name = "page", required = false) final Integer pageNumber,
            @RequestParam(name = "sort", required = false) final String sortColumn) {

        final GridStateDTO gridState = getListDTO(req);
        gridState.setPage(pageNumber);
        gridState.setSort(sortColumn, "id");

        final MapFilter filter = new MapFilter();
        prepareFilter(gridState, filter);

        final List<IMap> entities = mapService.find(filter);
        List<MapDTO> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());
        gridState.setTotalCount(mapService.getCount(filter));

        for (MapDTO mapDTO : dtos) {

            setStatusVisible(mapDTO);
        }
        final HashMap<String, Object> models = new HashMap<>();
        models.put("gridItem", dtos);
        return new ModelAndView("map.list", models);
    }

    private void setStatusVisible(MapDTO mapDTO) {
        if (mapDTO.getCustomerId().equals(getCustomerId()) || ("ADMIN".equals(getLoginRole()))) {
            mapDTO.setStatusVisible(true);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm() {
        final Map<String, Object> hashMap = new HashMap<>();
        MapDTO dto = new MapDTO();
        dto.setCustomerId(getCustomerId());
        hashMap.put("formModel", dto);
        return new ModelAndView("map.add", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam("fileDoc") final MultipartFile fileDoc,
            @Valid @ModelAttribute("formModel") final MapDTO formModel, final BindingResult result) throws IOException {
        if ((fileDoc.isEmpty()) || (result.hasErrors())) {
            return "map.add";
        } else {
            final IMap entity = fromDTOConverter.apply(formModel);
            entity.setImage(fileDoc.getBytes());
            mapService.save(entity);
            return "redirect:/map";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute("formModel") final MapDTO formModel, final BindingResult result)
            throws IOException {
        if (result.hasErrors()) {
            return "map.edit";
        } else {
            final IMap entity = fromDTOConverter.apply(formModel);
            mapService.save(entity);
            return "redirect:/map";
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
        mapService.delete(id);
        return "redirect:/map";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
        final IMap dbModel = mapService.get(id);
        final MapDTO dto = toDTOConverter.apply(dbModel);
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        hashMap.put("idHolder", new IdHolder());

        loadComboboxesModels(hashMap, id);
        return new ModelAndView("map.info", hashMap);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
        final MapDTO dto = toDTOConverter.apply(mapService.get(id));

        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);

        return new ModelAndView("map.edit", hashMap);
    }

    @RequestMapping(value = "/addWayToMap/{mapId}", method = RequestMethod.POST)
    public String addRouteToMap(@PathVariable(name = "mapId", required = true) final Integer mapId,

            @Valid @ModelAttribute("idHolder") final IdHolder idHolder, final BindingResult result) throws IOException {
        if (result.hasErrors()) {
            return "redirect:/map/" + mapId;
        } else {
            final IMap dbModel = mapService.getFullInfo(mapId);

            Integer routeId = idHolder.getId();
            IRoute route = routeService.get(routeId);
            dbModel.getRoutes().add(route);

            mapService.save(dbModel);

            return "redirect:/map/" + mapId;
        }
    }

    @RequestMapping(value = "/deleteWayFromMap/{id}", method = RequestMethod.POST)
    public ModelAndView deleteRouteFromMap(@PathVariable(name = "id", required = true) final Integer mapId,
            @Valid @ModelAttribute("idHolder") final IdHolder idHolder) {

        final IMap dbModel = mapService.getFullInfo(mapId);
        IRoute iRoute = routeService.get(idHolder.getId());
        LOGGER.info("routes on map before removing one=" + dbModel.getRoutes().size());
        dbModel.getRoutes().remove(iRoute);
        LOGGER.info("routes on map after removing one=" + dbModel.getRoutes().size());
        mapService.save(dbModel);

        final MapDTO dto = toDTOConverter.apply(dbModel);
        final HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("formModel", dto);

        loadComboboxesModels(hashMap, mapId);

        return new ModelAndView("map.info", hashMap);

    }

    private void loadComboboxesModels(final Map<String, Object> hashMap, Integer mapId) {

        addWaysOnMapToHashMap(hashMap, mapId);

        addDistsOnMapToHashMap(hashMap, mapId);

        addWaysToHashMap(hashMap);

        addDistToHashMap(hashMap);

    }

    private void addDistToHashMap(final Map<String, Object> hashMap) {
        List<IRoute> distancesList = routeService.getRoutesByTrack(Track.valueOf("DISTANCE"));

        Map<Integer, String> distances = distancesList.stream()
                .collect(Collectors.toMap(IRoute::getId, IRoute::getName));
        hashMap.put("distances", distances);
    }

    private void addWaysToHashMap(final Map<String, Object> hashMap) {
        if (getCustomerId() != null) {
            if (getLoginRole().equals("ADMIN")) {
                List<IRoute> waysList = routeService.getRoutesByTrack(Track.valueOf("WAY"));
                Map<Integer, String> ways = waysList.stream().collect(Collectors.toMap(IRoute::getId, IRoute::getName));
                hashMap.put("ways", ways);
            } else {

                List<IRoute> waysList = routeService.getCustomerRoutes(getCustomerId());
                Map<Integer, String> routesOnMapByCustomer = waysList.stream()
                        .collect(Collectors.toMap(IRoute::getId, IRoute::getName));
                hashMap.put("ways", routesOnMapByCustomer);
            }
        }
    }

    private void addDistsOnMapToHashMap(final Map<String, Object> hashMap, Integer mapId) {
        List<IRoute> distancesOnMapList = mapService.getRoutesOnMap(mapId, Track.valueOf("DISTANCE"));
        Map<Integer, String> distancesOnMap = distancesOnMapList.stream()
                .collect(Collectors.toMap(IRoute::getId, IRoute::getName));
        hashMap.put("distancesOnMap", distancesOnMap);
    }

    private void addWaysOnMapToHashMap(final Map<String, Object> hashMap, Integer mapId) {
        List<IRoute> waysOnMapList = mapService.getRoutesOnMap(mapId, Track.valueOf("WAY"));
        Map<Integer, String> waysOnMap = waysOnMapList.stream()
                .collect(Collectors.toMap(IRoute::getId, IRoute::getName));
        hashMap.put("waysOnMap", waysOnMap);

        if (getCustomerId() != null) {
            if (getLoginRole().equals("ADMIN")) {
                hashMap.put("myWaysOnMap", waysOnMap);
            } else {
                List<IRoute> myWaysList = new ArrayList<>();
                for (IRoute route : waysOnMapList) {
                    IRoute routeDB = routeService.get(route.getId());
                    if (routeDB.getCustomer().getId().equals(getCustomerId())) {
                        myWaysList.add(routeDB);
                    }
                }
                Map<Integer, String> myWaysOnMap = myWaysList.stream()
                        .collect(Collectors.toMap(IRoute::getId, IRoute::getName));
                hashMap.put("myWaysOnMap", myWaysOnMap);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/image/{mapId}")
    public void getImageAsByteArray(HttpServletResponse response,

            @PathVariable(name = "mapId", required = true) final Integer mapId) throws IOException {
        IMap entity = mapService.get(mapId);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        byte[] buf = entity.getImage();

        String encoded = Base64.getEncoder().encodeToString(buf);
        System.out.println(encoded);

        ByteArrayInputStream input = new ByteArrayInputStream(buf);
        IOUtils.copy(input, response.getOutputStream());
    }

}
