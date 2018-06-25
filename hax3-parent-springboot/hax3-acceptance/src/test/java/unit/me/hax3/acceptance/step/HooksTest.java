package unit.me.hax3.acceptance.step;

import cucumber.api.Scenario;
import me.hax3.acceptance.step.GenericHolder;
import me.hax3.acceptance.step.Hooks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Consumer;

import static cucumber.api.Result.Type.AMBIGUOUS;
import static cucumber.api.Result.Type.PASSED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static shiver.me.timbers.data.random.RandomStrings.someString;
import static shiver.me.timbers.data.random.RandomThings.someThing;

@ExtendWith(MockitoExtension.class)
class HooksTest {

    @Mock
    private List<GenericHolder> holders;

    @InjectMocks
    private Hooks hooks;

    @Test
    void Setup_does_nothing() {
        final Scenario scenario = mock(Scenario.class);
        // Given
        given(scenario.getName()).willReturn(someString());
        given(scenario.getStatus()).willReturn(someThing(AMBIGUOUS, PASSED));

        // When
        hooks.setup(scenario);
    }

    @Test
    void Tear_down_will_not_log_any_holders_if_the_scenario_passes() {

        final Scenario scenario = mock(Scenario.class);

        // Given
        given(scenario.isFailed()).willReturn(false);

        // When
        hooks.tearDown(scenario);

        // Then
        verifyZeroInteractions(holders);
    }

    @Test
    void Tear_down_will_log_all_holders_if_the_scenario_fails() {

        final Scenario scenario = mock(Scenario.class);

        // Given
        given(scenario.isFailed()).willReturn(true);

        // When
        hooks.tearDown(scenario);

        // Then
        then(holders).should().forEach(any(Consumer.class));
    }

    @Test
    void Tear_down_will_log_browser_logs_if_the_scenario_fails() {

        final Scenario scenario = mock(Scenario.class);

        // Given
        given(scenario.isFailed()).willReturn(true);

        // When
        hooks.tearDown(scenario);

        // Then
        then(holders).should().forEach(any(Consumer.class));
    }
}
