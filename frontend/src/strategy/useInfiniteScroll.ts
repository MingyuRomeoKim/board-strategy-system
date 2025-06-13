import { useEffect, useRef, useState, useCallback } from 'react';
import { Post } from '../types/post';
import axiosInstance from '../api/axiosInstance';

const PAGE_SIZE = 10;

export function useInfiniteScroll() {
    const [posts, setPosts] = useState<Post[]>([]);
    const [page, setPage] = useState(0);
    const [isEnd, setIsEnd] = useState(false);
    const [loading, setLoading] = useState(false);

    const observerRef = useRef<IntersectionObserver | null>(null);
    const targetRef = useRef<HTMLDivElement | null>(null);

    const loadMore = useCallback(async () => {
        if (loading || isEnd) return;

        setLoading(true);
        try {
            const res = await axiosInstance.get<Post[]>(
                `/posts?strategy=infinite&page=${page + 1}&size=${PAGE_SIZE}`
            );
            if (res.data.length < PAGE_SIZE) {
                setIsEnd(true);
            }
            setPosts((prev) => [...prev, ...res.data]);
            setPage((prev) => prev + 1);
        } catch (e) {
            console.error('무한스크롤 로딩 실패', e);
        } finally {
            setLoading(false);
        }
    }, [loading, isEnd, page]);

    useEffect(() => {
        loadMore(); // 첫 로딩
    }, []);

    useEffect(() => {
        if (!targetRef.current) return;

        observerRef.current = new IntersectionObserver(([entry]) => {
            if (entry.isIntersecting) {
                loadMore();
            }
        });

        observerRef.current.observe(targetRef.current);

        return () => {
            observerRef.current?.disconnect();
        };
    }, [loadMore]);

    return {
        posts,
        targetRef, // 마지막 div에 연결해야 함
        isEnd,
        loading,
    };
}
