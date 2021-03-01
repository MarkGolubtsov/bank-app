const serverUrl = 'http://localhost:8087';

export const endpoints = {
    users: `${serverUrl}/client/`,
    usersUpdate: (id: number) => `${serverUrl}/client/${id}`,
    cities: `${serverUrl}/supporting/cities?country=BLR`,
    nationalities: `${serverUrl}/supporting/countries`,
    martialStatuses: `${serverUrl}/supporting/martial-statuses`,
    disabilities: `${serverUrl}/supporting/disabilities`,
    gender: `${serverUrl}/supporting/gender`,
    countries: `${serverUrl}/supporting/countries`,
}