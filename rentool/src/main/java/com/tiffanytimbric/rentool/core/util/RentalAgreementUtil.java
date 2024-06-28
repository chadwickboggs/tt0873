package com.tiffanytimbric.rentool.core.util;

import com.tiffanytimbric.fsm.Event;
import com.tiffanytimbric.fsm.FiniteStateMachine;
import com.tiffanytimbric.fsm.State;
import com.tiffanytimbric.fsm.Transition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.function.Predicate;

public class RentalAgreementUtil {

    @NonNull
    public static FiniteStateMachine newRentalAgreementFsm() {
        final State declinedStateEndState = new State(
                "Declined", null
        );

        final State cancelledEndState = new State(
                "Cancelled", null
        );

        final State failedEndState = new State(
                "Failed", null
        );

        final State returnedEndState = new State(
                "Returned", null
        );

        final State pickedUpState = new State(
                "PickedUp", null,
                new Transition(
                        new Event("Fail", null),
                        null, failedEndState, null
                ),
                new Transition(
                        new Event("Return", null),
                        null, returnedEndState, null
                )
        );

        final State acceptedState = new State(
                "Accepted", null,
                new Transition(
                        new Event("Cancel", null),
                        null, cancelledEndState, null
                ),
                new Transition(
                        new Event("PickUp", null),
                        null, pickedUpState, null
                )
        );

        final State initialState = new State(
                "Proposed", null,
                new Transition(
                        new Event("Decline", null),
                        null, declinedStateEndState, null
                ),
                new Transition(
                        new Event("Accept", null),
                        null, acceptedState, null
                )
        );

        return new FiniteStateMachine(
                "RentalAgreement FSM", null, initialState
        );
    }

    private static class AllParticipantsPredicate implements Predicate<String> {
        @Override
        public boolean test(final String dataItem) {
            if (StringUtils.isBlank(dataItem)) {
                return false;
            }

            final List<String> dataItemList = List.of(dataItem.split(","));

            if (dataItemList.size() < 2) {
                return false;
            }

            return true;
        }
    }
}
