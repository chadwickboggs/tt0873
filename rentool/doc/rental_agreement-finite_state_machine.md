**States & Transitions**
* [Start] Proposed (User ID, Rental Agreement ID)
    * Accept (User ID, Rental Agreement ID) -> Accepted
    * Decline (User ID, Rental Agreement ID) -> DeclinedEndState
* Accepted (Rental Agreement ID)
    * PickUp (User ID, Rental Agreement ID) -> Partially PickedUp
    * Cancel (User ID, Rental Agreement ID, Reason) -> CancelledEndState
* PickedUp (Rental Agreement ID)
    * Return (User ID, Rental Agreement ID) -> ReturnedEndState
    * Fail (User ID, Rental Agreement ID, Reason) -> FailedEndState
* [End] ReturnedEndState (Rental Agreement ID)
* [End] DeclinedEndState (List of User ID's, Rental Agreement ID)
* [End] FailedEndState (List of User ID's, Rental Agreement ID)
* [End] CancelledEndState (List of User ID's, Rental Agreement ID, List of Reasons)
