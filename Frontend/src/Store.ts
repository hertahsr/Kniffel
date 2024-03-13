import {create} from 'zustand'

const useIdStore = create(() => ({
    id: 0,
}))

export default useIdStore