package neo.ehsanodyssey.advertisement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.ehsanodyssey.advertisement.exception.CheckPerformanceException;
import neo.ehsanodyssey.advertisement.exception.ServiceException;
import neo.ehsanodyssey.advertisement.rest.v1.response.PerformanceResponse;
import neo.ehsanodyssey.advertisement.rest.v1.response.TopFiveRecommendedAdvertisersResponse;
import neo.ehsanodyssey.advertisement.service.dto.ClickDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-13 Sep/Fri
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsService {

    private final ImpressionService impressionService;
    private final ClickService ClickService;

    public List<PerformanceResponse> checkPerformance() {
        try {
            var appContryGroupsClicksMap = this.getAppContryGroupsClicksMap();
            return appContryGroupsClicksMap
                    .entrySet()
                    .stream()
                    .flatMap(appGroup -> {
                        var appId = appGroup.getKey();
                        return appGroup.getValue()
                                .entrySet()
                                .stream()
                                .map(countryGroup -> {
                                    var countryCode = countryGroup.getKey();
                                    var appImpressionCount = this.impressionService
                                            .getByAppIdAndCountryCodeStream(appId, countryCode)
                                            .count();
                                    var appCountryClicks = countryGroup.getValue();
                                    var appClickCount = appCountryClicks.size();
                                    var sumOfAppCountryClicksRevenue =
                                            appCountryClicks.stream()
                                                    .mapToDouble(ClickDTO::revenue)
                                                    .sum();
                                    return PerformanceResponse.builder()
                                            .appId(appId)
                                            .countryCode(countryCode)
                                            .impressions(appImpressionCount)
                                            .clicks(appClickCount)
                                            .revenue(sumOfAppCountryClicksRevenue)
                                            .build();
                                });
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new CheckPerformanceException("unable_check_performance", e);
        }
    }

    public List<TopFiveRecommendedAdvertisersResponse> getTopFiveRecommendedAdvertisers() {
        try {
            var appContryGroupsClicksMap = this.getAppContryGroupsClicksMap();
            return appContryGroupsClicksMap
                    .entrySet()
                    .stream()
                    .flatMap(appGroup -> {
                        var appId = appGroup.getKey();
                        return appGroup.getValue()
                                .entrySet()
                                .stream()
                                .map(countryGroup -> {
                                    var countryCode = countryGroup.getKey();
                                    var appCountryImpressionGroupsSumRevenueMap = countryGroup.getValue()
                                            .stream()
                                            .collect(
                                                    toMap(
                                                            clickDTO -> clickDTO.impression().id(),
                                                            Function.identity(),
                                                            (lt, rt) -> ClickDTO.builder()
                                                                    .impression(lt.impression())
                                                                    .revenue(Double.sum(lt.revenue(), rt.revenue()))
                                                                    .build()
                                                    )
                                            );
                                    var recommendedAdvertiserIds = appCountryImpressionGroupsSumRevenueMap
                                            .values()
                                            .stream()
                                            .sorted(Comparator.comparingDouble(ClickDTO::revenue).reversed())
                                            .limit(5)
                                            .map(clickDTO -> clickDTO.impression().advertiserId())
                                            .collect(toList());
                                    return TopFiveRecommendedAdvertisersResponse.builder()
                                            .appId(appId)
                                            .countryCode(countryCode)
                                            .recommendedAdvertiserIds(recommendedAdvertiserIds)
                                            .build();
                                });
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new CheckPerformanceException("unable_check_performance", e);
        }
    }

    private Map<Integer, Map<String, List<ClickDTO>>> getAppContryGroupsClicksMap() {
        return this.ClickService.getAllStream()
                .collect(
                        groupingBy(
                                clickDTO -> clickDTO.impression().appId(),
                                groupingBy(
                                        clickDTO -> clickDTO.impression().countryCode(),
                                        toList()
                                )
                        )
                );
    }
}
