import { useCallback, useEffect, useRef, useState } from 'react';
import { Post } from '../types/post';
import axiosInstance from '../api/axiosInstance';

const PAGE_SIZE = 20;

export function useInfiniteScroll() {
    const [posts, setPosts] = useState<Post[]>([]);
    const [page, setPage] = useState(0);
    const [loading, setLoading] = useState(false);
    const [isEnd, setIsEnd] = useState(false);

    const observerRef = useRef<IntersectionObserver | null>(null);
    const targetRef = useRef<HTMLDivElement | null>(null);

    const loadMore = useCallback(async () => {
        if (loading || isEnd) return;

        setLoading(true);

        try {
            const res = await axiosInstance.get<Post[]>(
                `/posts?strategy=infinite&page=${page}&size=${PAGE_SIZE}`
            );

            const newPosts = res.data;

            if (newPosts.length < PAGE_SIZE) {
                setIsEnd(true);
            }

            setPosts((prev) => [...prev, ...newPosts]);
            setPage((prev) => prev + 1);
        } catch (err) {
            console.error('🚨 게시글 로딩 실패:', err);
        } finally {
            setLoading(false);
        }
    }, [page, loading, isEnd]);

    useEffect(() => {
        loadMore(); // 첫 페이지 자동 로딩
    }, []);

    const setTargetRef = useCallback((node: HTMLDivElement | null) => {
        if (observerRef.current) observerRef.current.disconnect();

        if (node) {
            observerRef.current = new IntersectionObserver(
                ([entry]) => {
                    if (entry.isIntersecting) {
                        loadMore();
                    }
                },
                {
                    root: null,
                    rootMargin: '0px 0px 200px 0px',
                    threshold: 0.1,
                }
            );
            observerRef.current.observe(node);
        }

        targetRef.current = node;
    }, [loadMore]);

    return {
        posts,
        targetRef: setTargetRef,
        loading,
        isEnd,
    };
}
