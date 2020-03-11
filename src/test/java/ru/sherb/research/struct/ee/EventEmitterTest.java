package ru.sherb.research.struct.ee;

import org.junit.jupiter.api.Test;

/**
 * @author maksim
 * @since 11.03.2020
 */
class EventEmitterTest {

    @Test
    public void test() {
        EventEmitter<PhoneNumber> phoneNumber = createPhoneNumber("123");
        EventEmitter<Email> email = createEmail("test@example.com");
        //todo ????
        // do
        //  number <- createPhoneNumber "123"
        //  email  <- createEmail "test@example.com"
        //  user   <- createUser "Ivan"
        //  updatePhoneNumber user number
        //  updateEmail user email
    }

    private EventEmitter<PhoneNumber> createPhoneNumber(String rawNumber) {
        PhoneNumber phoneNumber = new PhoneNumber(rawNumber);
        return new ObjectCreatedEvent<>(phoneNumber);
    }

    private EventEmitter<User> updatePhoneNumber(User user, PhoneNumber newPhoneNumber) {
        User updated = new User(user);
        updated.phoneNumber = newPhoneNumber;
        return new ObjectUpdatedEvent<>(user, updated);
    }

    private EventEmitter<User> updateEmail(User user, Email email) {
        User updated = new User(user);
        updated.email = email;
        return new ObjectUpdatedEvent<>(user, updated);
    }

    private EventEmitter<Email> createEmail(String rawEmail) {
        Email email = new Email(rawEmail);
        return new ObjectCreatedEvent<>(email);
    }

    private EventEmitter<User> createUser(String login) {
        User user = new User(login);
        return new ObjectCreatedEvent<>(user);
    }

    private static class PhoneNumber {

        private final String number;

        private PhoneNumber(String number) {
            this.number = number;
        }
    }

    private static class Email {

        private final String email;

        private Email(String email) {
            this.email = email;
        }
    }

    private static class User {

        private final String login;

        private PhoneNumber phoneNumber;
        private Email email;


        private User(String login) {
            this.login = login;
        }

        private User(User user) {
            this(user.login);
            this.email = user.email;
            this.phoneNumber = user.phoneNumber;
        }
    }
}