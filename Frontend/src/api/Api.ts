export async function post<I, O>(path: string, data: I) {
    const response = await fetch(path, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
    if (!response.ok) {
        throw Error(response.status + " " + response.statusText)
    }
    return await response.json() as Promise<O>
}