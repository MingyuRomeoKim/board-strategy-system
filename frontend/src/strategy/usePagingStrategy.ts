import { useEffect, useState } from 'react';
import { Post } from '../types/post';
import axiosInstance from '../api/axiosInstance';

const PAGE_SIZE = 20;

export function usePagingStrategy() {
    const [posts, setPosts] = useState<Post[]>([]);
    const [page, setPage] = useState(0);
    const [isEnd, setIsEnd] = useState(false);
    const [loading, setLoading] = useState(false);

    const loadMore = async () => {
        if (loading || isEnd) return;

        setLoading(true);
        try {
            const res = await axiosInstance.get<Post[]>(
                `/posts?strategy=paging&page=${page}&size=${PAGE_SIZE}`
            );
            if (res.data.length < PAGE_SIZE) {
                setIsEnd(true);
            }
            setPosts((prev) => [...prev, ...res.data]);
            setPage((prev) => prev + 1);
        } catch (e) {
            console.error('페이지 로딩 실패', e);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadMore(); // 첫 페이지 로딩
    }, []);

    return {
        posts,
        loadMore,
        isEnd,
        loading,
    };
}
