export async function post<I, O>(path: string, data: I) {
    const response = await fetch(path, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
    return await response.json() as Promise<O>
}