import { usePagingStrategy } from './usePagingStrategy';
import { useInfiniteScroll } from './useInfiniteScroll';
import { Post } from '../types/post';
import { RefObject } from 'react';

type StrategyType = 'paging' | 'infinite';

interface StrategyResult {
    posts: Post[];
    loadMore?: () => void;
    targetRef?: ((node: HTMLDivElement | null) => void);
    loading: boolean;
    isEnd: boolean;
}

export function useLoadStrategy(strategy: StrategyType): StrategyResult {
    // ❗ 두 훅을 무조건 호출해야 함
    const paging = usePagingStrategy();
    const infinite = useInfiniteScroll();

    if (strategy === 'paging') {
        return {
            posts: paging.posts,
            loadMore: paging.loadMore,
            isEnd: paging.isEnd,
            loading: paging.loading,
        };
    } else {
        return {
            posts: infinite.posts,
            targetRef: infinite.targetRef,
            isEnd: infinite.isEnd,
            loading: infinite.loading,
        };
    }
}
