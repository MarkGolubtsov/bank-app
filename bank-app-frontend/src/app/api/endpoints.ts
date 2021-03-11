const serverUrl = 'http://localhost:8087';

export const endpoints = {
    users: `${serverUrl}/clients/`,
    usersUpdate: (id: number) => `${serverUrl}/client/${id}`,
    cities: `${serverUrl}/supporting/cities?country=BLR`,
    nationalities: `${serverUrl}/supporting/countries`,
    martialStatuses: `${serverUrl}/supporting/martial-statuses`,
    disabilities: `${serverUrl}/supporting/disabilities`,
    currency: `${serverUrl}/supporting/currency`,
    gender: `${serverUrl}/supporting/gender`,
    countries: `${serverUrl}/supporting/countries`,
    createDep: (id: string) => `${serverUrl}/contracts/deposits/${id}`,
    createCredit: (id: string) => `${serverUrl}/contracts/credits/${id}`,
    contracts: `${serverUrl}/contracts`,
    withdraw: (id: string) => `${serverUrl}/contracts/deposits/withdraw/${id}`,
    accounts: `${serverUrl}/accounts`,
    closeDay:`${serverUrl}/supporting/close`
}