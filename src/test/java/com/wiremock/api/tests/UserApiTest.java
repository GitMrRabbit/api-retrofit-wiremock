package com.wiremock.api.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import retrofit2.Response;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import com.wiremock.api.model.request.CreateUserRequest;
import com.wiremock.api.model.response.CreateUserResponse;
import com.wiremock.api.model.request.UpdateUserRequest;
import com.wiremock.api.model.response.UpdateUserResponse;
import com.wiremock.api.model.response.UserResponse;
import com.wiremock.api.model.response.UsersResponse;

@Epic("API Testing")
@Feature("User Management")
@Owner("SL")
public class UserApiTest extends BaseTest {

    private final Faker faker = new Faker();

    @Test
    @Story("Get list of users")
    @Description("Test retrieving a list of users from page 1")
    @Severity(SeverityLevel.NORMAL)
    public void testGetUsers() throws IOException {
        Response<UsersResponse> response = reqresApiService.getUsers(1).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getData()).isNotNull();
        assertThat(response.body().getData().size()).isGreaterThan(0);
    }

    @ParameterizedTest(name = "page: {0}")
    @ValueSource(ints = {1, 2})
    @Story("Get list of users")
    @Description("Parameterized test for retrieving users from different pages")
    public void testGetUsersParameterized(int page) throws IOException {
        Response<UsersResponse> response = reqresApiService.getUsers(page).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getData()).isNotNull();
        assertThat(response.body().getPage()).isEqualTo(page);
    }

    @Test
    @Story("Get single user")
    @Description("Test retrieving a single user by ID")
    public void testGetUser() throws IOException {
        Response<UserResponse> response = reqresApiService.getUser(1).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getData()).isNotNull();
        assertThat(response.body().getData().getId()).isEqualTo(1);
    }

    @ParameterizedTest(name = "User ID: {0}")
    @ValueSource(ints = {1, 2, 3})
    @Story("Get single user")
    @Description("Parameterized test for retrieving different users by ID")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Get user with ID")
    public void testGetUserParameterized(int userId) throws IOException {
        Response<UserResponse> response = reqresApiService.getUser(userId).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getData()).isNotNull();
        assertThat(response.body().getData().getId()).isEqualTo(userId);
    }

    @Test
    @Story("Create user")
    @Description("Test creating a new user")
    public void testCreateUser() throws IOException {
        String name = faker.name().fullName();
        String job = faker.job().title();
        CreateUserRequest request = new CreateUserRequest(name, job);
        Response<CreateUserResponse> response = reqresApiService.createUser(request).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getName()).isNotNull();
        assertThat(response.body().getJob()).isNotNull();
        assertThat(response.body().getId()).isNotNull();
        assertThat(response.body().getCreatedAt()).isNotNull();
    }

    @Test
    @Story("Update user")
    @Description("Test updating a user with PUT")
    public void testUpdateUser() throws IOException {
        String name = faker.name().fullName();
        String job = faker.job().title();
        UpdateUserRequest request = new UpdateUserRequest(name, job);
        Response<UpdateUserResponse> response = reqresApiService.updateUser(1, request).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getName()).isNotNull();
        assertThat(response.body().getJob()).isNotNull();
        assertThat(response.body().getUpdatedAt()).isNotNull();
    }

    @Test
    @Story("Patch user")
    @Description("Test patching a user with PATCH")
    public void testPatchUser() throws IOException {
        String name = faker.name().fullName();
        String job = faker.job().title();
        UpdateUserRequest request = new UpdateUserRequest(name, job);
        Response<UpdateUserResponse> response = reqresApiService.patchUser(1, request).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.body()).isNotNull();
        assertThat(response.body().getName()).isNotNull();
        assertThat(response.body().getJob()).isNotNull();
        assertThat(response.body().getUpdatedAt()).isNotNull();
    }

    @Test
    @Story("Delete user")
    @Description("Test deleting a user")
    public void testDeleteUser() throws IOException {
        Response<Void> response = reqresApiService.deleteUser(1).execute();

        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.code()).isEqualTo(204);
    }

    @Test
    @Story("Negative test - Get non-existent user")
    @Description("Test retrieving a user that does not exist")
    public void testGetNonExistentUser() throws IOException {
        Response<UserResponse> response = reqresApiService.getUser(999).execute();

        assertThat(response.code()).isEqualTo(404);
        assertThat(response.body()).isNull();
    }

    @Test
    @Story("Negative test - Update non-existent user")
    @Description("Test updating a user that does not exist")
    public void testUpdateNonExistentUser() throws IOException {
        String name = faker.name().fullName();
        String job = faker.job().title();
        UpdateUserRequest request = new UpdateUserRequest(name, job);
        Response<UpdateUserResponse> response = reqresApiService.updateUser(999, request).execute();

        assertThat(response.code()).isEqualTo(404);
        assertThat(response.body()).isNull();
    }

    @Test
    @Story("Negative test - Delete non-existent user")
    @Description("Test deleting a user that does not exist")
    public void testDeleteNonExistentUser() throws IOException {
        Response<Void> response = reqresApiService.deleteUser(999).execute();

        assertThat(response.code()).isEqualTo(404);
    }
}
